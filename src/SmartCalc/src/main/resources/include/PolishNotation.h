#ifndef POLISH_NOTATION_H
#define POLISH_NOTATION_H

#include "StringQueue.h"

#include <string>
#include <stack>
#include <queue>
#include <unordered_map>
#include <cmath>
#include <algorithm>

namespace s21 {
    class PolishNotationModel {
    public:

        typedef struct CreditParam {
            double credit_sum;
            double credit_time;
            double percent;
            int type_payment;
        } CreditParam;

        typedef struct CreditResult {
            double first_payment;
            double last_payment;
            double overpayment;
            double total_payment;
        } CreditResult;

        typedef struct DepositParam {
            double deposit_sum;
            double deposit_time;
            double percent;
            int deposit_type;
            int start_date;
            int start_year;
        } DepositParam;


        typedef struct DepositResult {
            double total_percent_sum;
            double total_deposit_sum;
        } DepositResult;

        PolishNotationModel() = default;

        ~PolishNotationModel() = default;

        static bool isOperator(char c) {
            return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == 's' || c == 'c' || c == 't' || c == 'l' || c == 'a' || c == 'q' || c == 'm' || c == 'n' || c == 'S' || c == 'C';
        }

        static int getPrecedence(char op) {
            if (op == '+' || op == '-') return 1;
            if (op == '*' || op == '/') return 2;
            if (op == '^' || op == 'm') return 3;
            if (op == 's' || op == 'c' || op == 't' || op == 'l' || op == 'a' || op == 'q' || op == 'n' || op == 'S' || op == 'C') return 4; // sin, cos, tan, log, atan, sqrt, ln, asin, acos
            return 0;
        }

        static bool isDigit(char c) {
            return c >= '0' && c <= '9';
        }

        static bool isVariable(char c) {
            return c == 'x';
        }

        static s21::StringQueue infixToPostfix(const std::string& infixExpression) {
            std::stack<char> operators;
            std::queue<std::string> output;
            std::string number;

            for (size_t i = 0; i < infixExpression.length(); ++i) {
                char token = infixExpression[i];

                if (isDigit(token)) {
                    number += token;
                    if (i == infixExpression.length() - 1 || !isDigit(infixExpression[i + 1])) {
                        output.push(number);
                        number.clear();
                    }
                } else if (isVariable(token)) {
                    output.push(std::string(1, token));
                } else if (token == '(') {
                    operators.push(token);
                } else if (token == ')') {
                    while (!operators.empty() && operators.top() != '(') {
                        output.push(std::string(1, operators.top()));
                        operators.pop();
                    }
                    operators.pop();
                } else if (isOperator(token)) {
                    while (!operators.empty() && getPrecedence(operators.top()) >= getPrecedence(token)) {
                        output.push(std::string(1, operators.top()));
                        operators.pop();
                    }
                    operators.push(token);
                }
            }

            while (!operators.empty()) {
                output.push(std::string(1, operators.top()));
                operators.pop();
            }

            return output;
        }

        static double evaluatePostfix(s21::StringQueue postfixExpression, double x) {
            std::stack<double> stack;

            while (!postfixExpression.empty()) {
                std::string token = postfixExpression.front();
                postfixExpression.pop();

                if (isDigit(token[0]) || (token.size() > 1 && isDigit(token[1]))) {
                    stack.push(std::stod(token));
                } else if (isVariable(token[0])) {
                    stack.push(x);
                } else if (token == "+") {
                    double b = stack.top(); stack.pop();
                    double a = stack.top(); stack.pop();
                    stack.push(a + b);
                } else if (token == "-") {
                    double b = stack.top(); stack.pop();
                    double a = stack.top(); stack.pop();
                    stack.push(a - b);
                } else if (token == "*") {
                    double b = stack.top(); stack.pop();
                    double a = stack.top(); stack.pop();
                    stack.push(a * b);
                } else if (token == "/") {
                    double b = stack.top(); stack.pop();
                    double a = stack.top(); stack.pop();
                    stack.push(a / b);
                } else if (token == "^") {
                    double b = stack.top(); stack.pop();
                    double a = stack.top(); stack.pop();
                    stack.push(std::pow(a, b));
                } else if (token == "s") {
                    double a = stack.top(); stack.pop();
                    stack.push(std::sin(a));
                } else if (token == "c") {
                    double a = stack.top(); stack.pop();
                    stack.push(std::cos(a));
                } else if (token == "t") {
                    double a = stack.top(); stack.pop();
                    stack.push(std::tan(a));
                } else if (token == "l") {
                    double a = stack.top(); stack.pop();
                    stack.push(std::log10(a)); // log base 10
                } else if (token == "a") {
                    double a = stack.top(); stack.pop();
                    stack.push(std::atan(a));
                } else if (token == "q") {
                    double a = stack.top(); stack.pop();
                    stack.push(std::sqrt(a));
                } else if (token == "n") {
                    double a = stack.top(); stack.pop();
                    stack.push(std::log(a)); // natural log
                } else if (token == "S") {
                    double a = stack.top(); stack.pop();
                    stack.push(std::asin(a));
                } else if (token == "C") {
                    double a = stack.top(); stack.pop();
                    stack.push(std::acos(a));
                } else if (token == "m") {
                    double b = stack.top(); stack.pop();
                    double a = stack.top(); stack.pop();
                    stack.push(std::fmod(a, b)); // модульное деление
                }
            }

            return stack.top();
        }

        static std::string convertToCalculationFormat(const std::string& expression) {
            std::string result = expression;
            std::unordered_map<std::string, std::string> conversions = {
                    {"sin", "s"},
                    {"cos", "c"},
                    {"tan", "t"},
                    {"log", "l"},
                    {"atan", "a"},
                    {"sqrt", "q"},
                    {"ln", "n"},
                    {"asin", "S"},
                    {"acos", "C"}
            };

            for (const auto& pair : conversions) {
                size_t pos = 0;
                while ((pos = result.find(pair.first, pos)) != std::string::npos) {
                    result.replace(pos, pair.first.length(), pair.second);
                    pos += pair.second.length();
                }
            }

            return result;
        }

        static double mainCalculation(const std::string& expression, double xValue) {
            std::string calcExpression = convertToCalculationFormat(expression);
            s21::StringQueue postfixExpression = infixToPostfix(calcExpression);
            return evaluatePostfix(postfixExpression, xValue);
        }

        static DepositResult calculateDeposit(s21::PolishNotationModel::DepositParam param) {
            s21::PolishNotationModel::DepositResult result;
            double start_deposit_sum = param.deposit_sum;
            double days[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            int year = param.start_year;
            double days_count = 0;
            int i = param.start_date - 1;
            double deposit_time_tmp = param.deposit_time;
            while (deposit_time_tmp > 0) {
                if (year % 4 != 0) {
                    days[1] = 28;
                } else {
                    days[1] = 29;
                }
                days_count += days[i];
                if (i == 11) {
                    i = 0;
                    year++;
                } else {
                    i++;
                }
                deposit_time_tmp--;
            }

            if (param.deposit_type) {
                result.total_deposit_sum =
                        start_deposit_sum * pow(1 + param.percent / 1200, param.deposit_time);
                result.total_percent_sum = result.total_deposit_sum - start_deposit_sum;
            } else {
                result.total_percent_sum =
                        start_deposit_sum * param.percent * days_count / (365 * 100);
            }
            result.total_deposit_sum = result.total_percent_sum + start_deposit_sum;
            return result;
        }

        static CreditResult calculateCredit(s21::PolishNotationModel::CreditParam param) {
            PolishNotationModel::CreditResult result;
            double sd = param.credit_sum / param.credit_time;
            double sp = 1.0;
            double start_credit_sum = param.credit_sum;
            if (param.type_payment) {
                double days[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                int i = 0;
                param.percent = param.percent / 100.0;
                result.total_payment = 0.0;
                result.first_payment =
                        sd + param.credit_sum * param.percent * days[0] / 365.0;
                while (param.credit_sum > 0) {
                    sp = param.credit_sum * param.percent * days[i] / 365.0;
                    param.credit_sum = param.credit_sum - sd;
                    result.total_payment = result.total_payment + sd + sp;
                    if (i == 11) {
                        i = 0;
                    } else {
                        i++;
                    }
                }
                result.last_payment = sp + sd;
            } else {
                param.percent = param.percent / 1200;
                result.last_payment = result.first_payment =
                        param.credit_sum * param.percent /
                        (1 - pow((1 + param.percent), (0 - param.credit_time)));
                result.total_payment = result.first_payment * param.credit_time;
            }
            result.overpayment = result.total_payment - start_credit_sum;
            return result;
        }



    };
}



#endif // POLISH_NOTATION_H

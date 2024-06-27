

#ifndef POLISHNOTATION_STRINGQUEUE_H
#define POLISHNOTATION_STRINGQUEUE_H

#include <string>
#include <queue>

namespace s21 {
    class StringQueue {
    public:
        StringQueue() = default;
        StringQueue(std::queue<std::string> queue) {
            this->queue = queue;
        }
        ~StringQueue() = default;

        void push(const std::string& str) { queue.push(str); }

        void pop() { queue.pop(); }

        std::string front() { return queue.front(); }

        bool empty() { return queue.empty(); }

    private:
        std::queue<std::string> queue;
    };
}

#endif //POLISHNOTATION_STRINGQUEUE_H

def solution(N, number):
    if number == N:
        return 1
    values = {}
    tmp = 0
    for i in range(1, 9):
        tmp += N
        if number == tmp: 
            return i
        values[i] = {tmp}
        tmp *= 10
    
    eval = 0
    for i in range(2, 9):
        for j in range(1, i // 2 + 1):
            for x in values[i - j]:
                for y in values[j]:
                    for z in range(6):
                        if z == 0:
                            eval = x + y
                        elif z == 1:
                            eval = x * y
                        elif z == 2:
                            eval = x - y
                        elif z == 3:
                            eval = y - x
                        elif z == 4:
                            eval = x // y
                        elif z == 5:
                            eval = y // x
                        if eval > 0:
                            if eval == number:
                                return i
                            values[i].add(eval)
    return -1
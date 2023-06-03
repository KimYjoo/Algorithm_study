def is_safe(board, row, col, N):
    # 같은 열에 있는 다른 퀸 확인
    for i in range(row):
        if board[i][col] == "Q":
            return False

    # 왼쪽 대각선 방향 확인
    i, j = row, col
    while i >= 0 and j >= 0:
        if board[i][j] == "Q":
            return False
        i -= 1
        j -= 1

    # 오른쪽 대각선 방향 확인
    i, j = row, col
    while i >= 0 and j < N:
        if board[i][j] == "Q":
            return False
        i -= 1
        j += 1

    return True


def solve_nqueens_util(board, row, N, solutions):
    if row == N:
        # 모든 퀸을 배치한 경우 해를 찾음
        solutions.append([row[:] for row in board])
        return

    for col in range(N):
        if is_safe(board, row, col, N):
            # 현재 위치에 퀸을 배치
            board[row][col] = "Q"

            # 다음 행으로 이동하여 퀸을 배치
            solve_nqueens_util(board, row + 1, N, solutions)

            # 현재 위치에 배치한 퀸을 제거하고 다른 위치를 시도
            board[row][col] = "X"


def solve_nqueens(N):
    board = [["X"] * N for _ in range(N)]
    solutions = []
    solve_nqueens_util(board, 0, N, solutions)

    if len(solutions) == 0:
        print("해가 존재하지 않습니다.")
        return

    # 모든 가능한 경우의 수 출력
    for solution in solutions:
        for i in range(N):
            for j in range(N):
                print(solution[i][j], end=" ")
            print()
        print()


# N-Queens 문제를 푸는 예시
N = 1
solve_nqueens(N)
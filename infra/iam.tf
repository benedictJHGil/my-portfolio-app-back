data "aws_iam_policy_document" "task_exec_assume" {
    statement {
        actions = ["sts:AssumeRole"]
        principals {
        type        = "Service"
        identifiers = ["ecs-tasks.amazonaws.com"]
        }
    }
}

resource "aws_iam_role" "task_exec_role" {
    name               = "portfolio-task-exec"
    assume_role_policy = data.aws_iam_policy_document.task_exec_assume.json
}

# ECR Pull + CloudWatch Logs 에 필요한 실행 정책
resource "aws_iam_role_policy_attachment" "task_exec_attach" {
    role       = aws_iam_role.task_exec_role.name
    policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy"
}
resource "aws_iam_user" "s3_user_spring_boot_jupiter" {
    name = "s3_user_spring_boot_jupiter"
}

resource "aws_iam_access_key" "s3_user_access_key" {
    user = aws_iam_user.s3_user_spring_boot_jupiter.name
}


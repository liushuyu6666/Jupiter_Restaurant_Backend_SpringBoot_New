resource "aws_iam_policy" "s3_bucket_policy_spring_boot_jays" {
    name   = "s3_bucket_policy_spring_boot_jays"
    description = "Allows read and write access to the 'springbootjays' bucket"
    policy = <<EOF
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "AllowReadWriteAccessToBucket",
            "Effect": "Allow",
            "Action": [
                "s3:GetObject",
                "s3:PutObject",
                "s3:ListBucket"
            ],
            "Resource": [
                "arn:aws:s3:::springbootjays",
                "arn:aws:s3:::springbootjays/*"
            ]
        }
    ]
}
EOF
}

resource "aws_iam_user_policy_attachment" "s3_user_attachment_spring_boot_jays" {
    user       = "s3_user_spring_boot_jays"
    policy_arn = aws_iam_policy.s3_bucket_policy_spring_boot_jays.arn
}
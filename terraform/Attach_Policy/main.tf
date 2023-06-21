resource "aws_iam_policy" "s3_bucket_policy_spring_boot_jupiter" {
    name   = "s3_bucket_policy_spring_boot_jupiter"
    description = "Allows read and write access to the 'springbootjupiter' bucket"
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
                "arn:aws:s3:::springbootjupiter",
                "arn:aws:s3:::springbootjupiter/*"
            ]
        }
    ]
}
EOF
}

resource "aws_iam_user_policy_attachment" "s3_user_attachment_spring_boot_jupiter" {
    user       = "s3_user_spring_boot_jupiter"
    policy_arn = aws_iam_policy.s3_bucket_policy_spring_boot_jupiter.arn
}
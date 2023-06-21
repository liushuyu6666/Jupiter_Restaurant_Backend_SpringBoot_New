# TODO: configure in the .env
resource "aws_s3_bucket" "spring_boot_jupiter" {
    bucket = "springbootjupiter"

    tags = {
        Name        = "Jupiter"
        Environment = "Dev"
    }
}

resource "aws_s3_bucket_policy" "bucket_policy_spring_boot_jupiter" {
    bucket = aws_s3_bucket.spring_boot_jupiter.id

    policy = <<EOF
    {
        "Version": "2012-10-17",
        "Id": "RestrictAccessToSpringBootJaysBucket",
        "Statement": [
            {
                "Sid": "AllowS3UserSpringBootJupiter",
                "Effect": "Allow",
                "Principal": {
                    "AWS": "arn:aws:iam::${var.account_number}:user/s3_user_spring_boot_jupiter"
                },
                "Action": [
                    "s3:GetObject",
                    "s3:PutObject",
                    "s3:ListBucket"
                ],
                "Resource": [
                    "arn:aws:s3:::springbootjupiter",
                    "arn:aws:s3:::springbootjupiter/*"
                ]
            },
            {
                "Sid": "DenyAccessToOthers",
                "Effect": "Deny",
                "NotPrincipal": {
                    "AWS": "arn:aws:iam::${var.account_number}:user/s3_user_spring_boot_jupiter"
                },
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

output "bucket_name" {
    value = aws_s3_bucket.spring_boot_jupiter.bucket
}

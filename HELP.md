# Getting Started

## Prepare application.properties
1. Copy and paste the `application.properties.example` and rename it to `application.properties`.
2. Replace some fields with your own values.

## Use Terraform to create IAM and S3 Bucket
Use Terraform script to create an IAM user, a S3 bucket and attach policies to these resources.
1. Create an AWS account, once the account is created, you are automatically assigned a root user. However, since the root user is not recommended to use in the project, AWS will not generate access key for it by default.
2. So, you need to create a new user (`TerraformInJays`) with all permissions to IAM and S3.
   1. The policy to create IAM user
   ```json
   {
      "Version": "2012-10-17",
      "Statement": [
        {
            "Sid": "VisualEditor0",
            "Effect": "Allow",
            "Action": "iam:*",
            "Resource": "*"
        }
      ]
    }
   ```
   2. The policy to create S3
   ```json
   {
      "Version": "2012-10-17",
      "Statement": [
        {
            "Sid": "VisualEditor0",
            "Effect": "Allow",
            "Action": "s3:*",
            "Resource": "*"
        }
      ]
   }
   ```
3. Create two credentials files:
   1. Copy and paste `credentials.tf.example` to `credentials.tf` and replace sensitive values.
   2. Copy and paste `S3/credentials.tf.exampel` to `S3/credentials.tf` and replace sensitive values.
4. Begin to run terraform scripts:
   1. Install terraform from https://developer.hashicorp.com/terraform/downloads
   2. Navigate to the terraform folder and run `terraform init`, then
   3. run `terraform plan`, finally
   4. run `terraform apply`.
5. After applying to terraform scripts, these four fields will appear in `application.properties`
   1. `aws.S3.properties.accessKey`
   2. `aws.S3.properties.secretKey`
   3. `aws.S3.properties.region`
   4. `aws.S3.properties.bucketName`
6. Summary: 
   1. There are three users: root user (created when open an account).
   2. `TerraformInJays`: Use for terraform scripts. S3 bucket `springbootjays` and IAM user `s3_user_spring_boot_jays` will be generated under this user.
   3. `s3_user_spring_boot_jays`: For this project only, credentials will be generated at the end of the `application.properties`.

## Install mongodb
### Local
Don't forget to install mongodb on-premise if you want to demo it locally. The database name should be `Spring_Boot_Jays`, which is defined in `application.properties`.

# Swagger
Swagger is installed so after running this project, go to http://localhost:8080/swagger-ui/ to check all APIs.



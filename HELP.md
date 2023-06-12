# Getting Started

## Prepare application.properties
1. Copy and paste the `application.properties.example` and rename it to `application.properties`.
2. Replace some fields with your own values.

## Create S3 bucket
Using `s3.tf` under the terraform folder to create a S3 bucket.
1. Install terraform from https://developer.hashicorp.com/terraform/downloads
2. Configure AWS credentials. [TODO: create a specific user for S3]
3. Navigate to the terraform folder and 
   1. run `terraform init`, then
   2. run `terraform plan`, finally
   3. run `terraform apply`.

## Install mongodb
### Local
Don't forget to install mongodb on-premise if you want to demo it locally. The database name should be `Spring_Boot_Jays`, which is defined in `application.properties`.

# Swagger
Swagger is installed so after running this project, go to http://localhost:8080/swagger-ui/ to check all APIs.



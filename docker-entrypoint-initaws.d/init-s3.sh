echo "test=moja wartosc" > sample.properties
awslocal s3api put-object --bucket my-bucket --key sample.properties --body sample.properties
rm sample.properties

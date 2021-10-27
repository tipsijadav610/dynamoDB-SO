## CLI Scripts

#### Users Table
 aws dynamodb create-table --table-name Users --attribute-definitions\
 AttributeName=user_id,AttributeType=N --key-schema\
 AttributeName=user_id,KeyType=HASH --provisioned-throughput\\
 ReadCapacityUnits=10,WriteCapacityUnits=5 --endpoint-url http://localhost:8000
 
 #### Questions Table
 aws dynamodb create-table --table-name Questions --attribute-definitions\
AttributeName=qid,AttributeType=NAttributeName=user_id,AttributeType=N --key-schema\
AttributeName=qid,KeyType=HASH --provisioned-throughput\
ReadCapacityUnits=10,WriteCapacityUnits=5 --endpoint-url http://localhost:8000 --global-secondary-indexes file://cli_gsi.json

 #### Answers Table
 aws dynamodb create-table --table-name Answers --attribute-definitions\
AttributeName=qid,AttributeType=N AttributeName=answer_seq_no,AttributeType=N\ 
AttributeName=user_id,AttributeType=N --key-schema AttributeName=qid,KeyType=HASH\
AttributeName=answer_seq_no,KeyType=RANGE --provisioned-throughput\
ReadCapacityUnits=10,WriteCapacityUnits=5 --endpoint-url http://localhost:8000 --global-secondary-indexes file://cli_gsi.json
 

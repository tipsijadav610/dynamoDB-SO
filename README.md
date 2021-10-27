# dynamo DB database for “My Stack Overflow”

#### Let us say we identify three tables - Users, Questions, and Answers for this purpose.
* Table: Users\
  Key: user_id\
  other attributes: password, email\

* Table: Questions\
  Key: qid (hash)\
  Index: user_index: user_id (Hash), qid (range)\
  other attributes: timestamp, tags\

* Table: Answers\
  Key: qid (Hash), answer_seq_no (Range)\
  Index: user_index: user_id (hash), qid (range)\
  other attributes: timestamp, thump_up, thumps_down\

#### Do following here:
 1. Create said tables on your Dynamo DB server.
 2. Write functions for performing following operations on MySO database.\
    a. Add three users. required attributes - user_id, user_name, password, email\
    b. Add five questions. Required attributes - qid, user_id, question-text, timestamp, tags\
    c. Add about 10 answers to any 4 questions  qid, user_id, answer_seq_no, answer-text, timestamp\
    d. Visitor rates an answer (increase and update respective count for ThumpUp or Thumps Down)\
    e. List all questions posted by an user\
    f. List all answers for given “qid”\
    g. List all answers from a given user-id\
    h. List All answers for given user-name that has ThumpsUp count\

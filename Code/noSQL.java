package Lab07.assignment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.sql.Timestamp;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;


public class noSQL {

	public static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "ap-south-1"))
            .build();
	
	public static DynamoDBMapper mapper = new DynamoDBMapper(client);
	
	public static void Question_a(int user_id, String user_name, String password, String email){
		Users item = new Users();
        item.setuser_id(user_id);
        item.setuser_name(user_name);
        item.setpassword(password);
        item.setemail(email);

        mapper.save(item);
        
        Users itemRetrieved = mapper.load(Users.class, user_id);
        System.out.println(itemRetrieved); 
	}
	
	public static void Question_b(int qid, int user_id,String question_text, String timestamp, String tags){
		Questions item = new Questions();
        item.setqid(qid);
        item.setuser_id(user_id);
        item.setquestion_text(question_text);
        item.settimestamp(timestamp);
        item.settags(tags);

        mapper.save(item);
        
        Questions itemRetrieved = mapper.load(Questions.class, qid);
        System.out.println(itemRetrieved);
	}
	
	public static void Question_c(int qid, int answer_seq_no, int user_id, String answer_text, String timestamp, int thumbs_up, int thumbs_down){
		Answers item = new Answers();
        item.setqid(qid);
        item.setanswer_seq_no(answer_seq_no);
        item.setuser_id(user_id);
        item.setanswer_text(answer_text);
        item.settimestamp(timestamp);
        item.setthumbs_up(thumbs_up);
        item.setthumbs_down(thumbs_down);

        mapper.save(item);
        
        Answers itemRetrieved = mapper.load(Answers.class, qid, answer_seq_no);
        System.out.println(itemRetrieved);
	}
	
	public static void Question_d(int qid, int answer_seq_no, int thumbs_up, int thumbs_down) throws Exception {
		Answers item = new Answers();
		
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		
	    Answers itemRetrieved = mapper.load(Answers.class, qid, answer_seq_no);

        itemRetrieved.setthumbs_up(thumbs_up);
        itemRetrieved.setthumbs_down(thumbs_down);
        mapper.save(itemRetrieved);

        DynamoDBMapperConfig config = DynamoDBMapperConfig.builder()
            .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
            .build();
        Answers updatedItem = mapper.load(Answers.class, qid, answer_seq_no);
 
        System.out.println(updatedItem);
	}
	
	public static void Question_e(Integer user_id){
		String temp = user_id.toString();
		
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val", new AttributeValue().withN(temp));
        
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("user_id = :val").withExpressionAttributeValues(eav);

        PaginatedScanList<Questions> scanResult = mapper.scan(Questions.class, scanExpression);

        for (Questions ques : scanResult) {
            System.out.println(ques);
        }
	}
	
	public static void Question_f(Integer qid){
		String temp = qid.toString();
		
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val", new AttributeValue().withN(temp));
        
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("qid = :val").withExpressionAttributeValues(eav);

        PaginatedScanList<Answers> scanResult = mapper.scan(Answers.class, scanExpression);

        for (Answers ans : scanResult) {
            System.out.println(ans);
        }
	}
	
	public static void Question_g(Integer user_id){
		String temp = user_id.toString();
		
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val", new AttributeValue().withN(temp));
        
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("user_id = :val").withExpressionAttributeValues(eav);

        PaginatedScanList<Answers> scanResult = mapper.scan(Answers.class, scanExpression);

        for (Answers ans : scanResult) {
             System.out.println(ans);
        }
	}
	
	public static void Question_h(String user_name){
		Map<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
        eav1.put(":val", new AttributeValue().withS(user_name));
        
        DynamoDBScanExpression scanExpression1 = new DynamoDBScanExpression()
                .withFilterExpression("user_name = :val").withExpressionAttributeValues(eav1);

        PaginatedScanList<Users> scanResult1 = mapper.scan(Users.class, scanExpression1);

        for (Users usr : scanResult1) {
        	String temp = (usr.getuser_id()).toString();
   
        	Map<String, AttributeValue> eav2 = new HashMap<String, AttributeValue>();
            eav2.put(":val1", new AttributeValue().withN(temp));
            eav2.put(":val2", new AttributeValue().withN("0"));
            
            DynamoDBScanExpression scanExpression2 = new DynamoDBScanExpression()
                    .withFilterExpression("user_id = :val1 and thumbs_up > :val2").withExpressionAttributeValues(eav2);
            
            PaginatedScanList<Answers> scanResult2 = mapper.scan(Answers.class, scanExpression2);
            
            for (Answers ans : scanResult2) {
                System.out.println(ans);
           }
        }
	}
	
    public static void main(String[] args) throws Exception {
    	System.out.println("Add three users");
    	Question_a(101, "User1", "u1", "user1@gmail.com");
    	Question_a(102, "User2", "u2", "user2@gmail.com");
    	Question_a(103, "User3", "u3", "user3@gmail.com");

    	System.out.println("");
    	long offset1 = Timestamp.valueOf("2020-01-01 00:00:00").getTime();
    	long end1 = Timestamp.valueOf("2020-02-01 00:00:00").getTime();
    	long diff1 = end1 - offset1 + 1;
    	System.out.println("Add five questions");
    	Timestamp q1 = new Timestamp(offset1 + (long)(Math.random() * diff1));
    	Question_b(1, 101, "Question1", q1.toString(),"tag1");
        Timestamp q2 = new Timestamp(offset1 + (long)(Math.random() * diff1));
        Question_b(2, 102, "Question2", q2.toString(),"tag2");
        Timestamp q3 = new Timestamp(offset1 + (long)(Math.random() * diff1));
        Question_b(3, 103, "Question3", q3.toString(),"tag3");
        Timestamp q4 = new Timestamp(offset1 + (long)(Math.random() * diff1));
        Question_b(4, 101, "Question4", q4.toString(),"tag4");
        Timestamp q5 = new Timestamp(offset1 + (long)(Math.random() * diff1));
        Question_b(5, 102, "Question5", q5.toString(),"tag5");
        
    	long offset2 = Timestamp.valueOf("2020-02-01 00:00:00").getTime();
    	long end2 = Timestamp.valueOf("2020-03-01 00:00:00").getTime();
    	long diff2 = end2 - offset2 + 1;
    	System.out.println("");
    	System.out.println("Add about 10 answers to any 4 questions");
        Timestamp a1 = new Timestamp(offset2 + (long)(Math.random() * diff2));
        Question_c(1, 1, 101, "Answer1", a1.toString(), 0, 0);
        Timestamp a2 = new Timestamp(offset2 + (long)(Math.random() * diff2));
        Question_c(2, 1, 102, "Answer2", a2.toString(), 0, 0);
        Timestamp a3 = new Timestamp(offset2 + (long)(Math.random() * diff2));
        Question_c(3, 1, 103, "Answer3", a3.toString(), 0, 0);
        Timestamp a4 = new Timestamp(offset2 + (long)(Math.random() * diff2));
        Question_c(4, 1, 101, "Answer4", a4.toString(), 0, 0);
        Timestamp a5 = new Timestamp(offset2 + (long)(Math.random() * diff2));
        Question_c(1, 2, 102, "Answer5", a5.toString(), 0, 0);
        Timestamp a6 = new Timestamp(offset2 + (long)(Math.random() * diff2));
        Question_c(2, 2, 103, "Answer6", a6.toString(), 0, 0);
        Timestamp a7 = new Timestamp(offset2 + (long)(Math.random() * diff2));
        Question_c(3, 2, 101, "Answer7", a7.toString(), 0, 0);
        Timestamp a8 = new Timestamp(offset2 + (long)(Math.random() * diff2));
        Question_c(4, 2, 102, "Answer8", a8.toString(), 0, 0);
        Timestamp a9 = new Timestamp(offset2 + (long)(Math.random() * diff2));
        Question_c(1, 3, 103, "Answer9", a9.toString(), 0, 0);
        Timestamp a10 = new Timestamp(offset2 + (long)(Math.random() * diff2));
        Question_c(2, 3, 101, "Answer10", a10.toString(), 0, 0);
        
        System.out.println("");
    	System.out.println("Visitor rates an answer");
    	Question_d(1, 1, 0, 8);
    	Question_d(2, 1, 5, 6);
    	Question_d(3, 1, 10, 6);
    	Question_d(4, 1, 10, 10);
    	Question_d(1, 2, 2, 7);
    	Question_d(2, 2, 9, 8);
    	Question_d(3, 2, 3, 11);
    	Question_d(4, 2, 6, 5);
    	Question_d(1, 3, 0, 1);
    	Question_d(2, 3, 4, 3);
    	
    	System.out.println("");
    	System.out.println("List all questions posted by an user");
    	Question_e(101);
    	
    	System.out.println("");
    	System.out.println("List all answers for given qid");
    	Question_f(4);
    	
    	System.out.println("");
    	System.out.println("List all answers from a given user_id");
    	Question_g(102);
    	
    	System.out.println("");
    	System.out.println("List all answers for given user_name that has Thumps_up count");
    	Question_h("User3");
    }
}
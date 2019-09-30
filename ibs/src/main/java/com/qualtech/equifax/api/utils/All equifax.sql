--QCIB_EQ_E Table-----

CREATE TABLE QCIB_EQ_E_ADRS_INFO 
   (	ADDRESS_INFO_ID NUMBER(19,0) NOT NULL ENABLE, 
	ADDRESS VARCHAR2(255 CHAR), 
	POSTAL VARCHAR2(255 CHAR), 
	REPORTED_DATE VARCHAR2(255 CHAR), 
	STATE VARCHAR2(255 CHAR), 
	TYPE VARCHAR2(255 CHAR), 
	REQUEST_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (ADDRESS_INFO_ID));
     commit;
 
 CREATE SEQUENCE  QCIB_EQ_E_ADRS_INFO_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
   commit;
 
 CREATE TABLE QCIB_EQ_E_PAN_DTL
   (	PANDETAIL_ID NUMBER NOT NULL ENABLE, 
	SEQ VARCHAR2(20 BYTE), 
	PANNUMBER VARCHAR2(20 BYTE), 
	REPORTEDDATE VARCHAR2(100 BYTE), 
	REQUEST_UNIQUE_ID NUMBER
   );
   commit;
 
 CREATE SEQUENCE  QCIB_EQ_E_PAN_DTL_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  COMMIT;
 
   CREATE TABLE QCIB_EQ_E_VOTER_DTLS 
   (	VOTERDETAIL_ID NUMBER NOT NULL ENABLE, 
	SEQ VARCHAR2(20 BYTE), 
	REQUEST_UNIQUE_ID NUMBER, 
	VOTERIDNUM VARCHAR2(20 BYTE), 
	REPORTEDDATE VARCHAR2(100 BYTE)
   ) ;
CREATE SEQUENCE  QCIB_EQ_E_VOTER_DTLS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  COMMIT;
  
   CREATE TABLE QCIB_EQ_E_EMAIL_DTLS 
   (	EMAILDETAILS_ID NUMBER NOT NULL ENABLE, 
	SEQ VARCHAR2(50 BYTE), 
	EMAILADDRESS VARCHAR2(200 BYTE), 
	REPORTEDDATE VARCHAR2(20 BYTE), 
	REQUEST_UNIQUE_ID NUMBER
   );
   COMMIT;

   CREATE SEQUENCE  QCIB_EQ_E_EMAIL_DTLS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
 
 
CREATE TABLE QCIB_EQ_E_DTLS_LOGS 
   (	REQUEST_UNIQUE_ID NUMBER(19,0) NOT NULL ENABLE, 
	CREATED_TIME TIMESTAMP (6), 
	DISCLAIMER CLOB DEFAULT EMPTY_CLOB(), 
	REMARKS VARCHAR2(255 CHAR), 
	REQUEST_INFO_JSON CLOB DEFAULT EMPTY_CLOB(), 
	REQUEST_XML CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_DATE CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_ERROR_INFO_JSON CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_ORDER_NO VARCHAR2(255 CHAR), 
	RESPONSE_TIME VARCHAR2(255 CHAR), 
	RESPONSE_XML CLOB DEFAULT EMPTY_CLOB(), 
	TRACKER_ID VARCHAR2(255 CHAR), 
	UPDATED_TIME TIMESTAMP (6), 
	REQUEST_JSON CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_JSON CLOB DEFAULT EMPTY_CLOB(), 
	HTMLDATA CLOB DEFAULT EMPTY_CLOB(), 
	PDF_BYTE_ARRAY CLOB DEFAULT EMPTY_CLOB(), 
	 PRIMARY KEY (REQUEST_UNIQUE_ID));
     commit;
  CREATE SEQUENCE  QCIB_EQ_E_DTLS_LOGS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
     commit;
     

      CREATE TABLE QCIB_EQ_E_NSDL_REQ 
   (	NSDL_REQUEST_ID NUMBER(19,0) NOT NULL ENABLE, 
	PAN_NUMBER VARCHAR2(255 CHAR), 
	SOURCE VARCHAR2(255 CHAR), 
	REQUEST_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (NSDL_REQUEST_ID));
     commit;
  CREATE SEQUENCE  QCIB_EQ_E_NSDL_REQ_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
     commit;
     
     
      CREATE TABLE QCIB_EQ_E_NSDL_RES 
   (	NSDL_RESPONSE_ID NUMBER(19,0) NOT NULL ENABLE, 
	FIRST_NAME VARCHAR2(255 CHAR), 
	LAST_NAME VARCHAR2(255 CHAR), 
	LAST_UPDATED_DATE VARCHAR2(255 CHAR), 
	PAN VARCHAR2(255 CHAR), 
	PERCENTAGE_MATCH VARCHAR2(255 CHAR), 
	RETURN_CODE VARCHAR2(255 CHAR), 
	RETURN_CODE_DESC VARCHAR2(255 CHAR), 
	TITLE VARCHAR2(255 CHAR), 
	REQUEST_UNIQUE_ID NUMBER(19,0), 
    NSDL_RES_ID VARCHAR2(20 BYTE),
	 PRIMARY KEY (NSDL_RESPONSE_ID));
     commit;
   CREATE SEQUENCE  QCIB_EQ_E_NSDL_RES_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
     
     
      CREATE TABLE QCIB_EQ_E_PRSNL_INFO 
   (	PERSONAL_INFO_ID NUMBER(19,0) NOT NULL ENABLE, 
	AGE VARCHAR2(255 CHAR), 
	DATE_OF_BIRTH VARCHAR2(255 CHAR), 
	FIRST_NAME VARCHAR2(255 CHAR), 
	GENDER VARCHAR2(255 CHAR), 
	LAST_NAME VARCHAR2(255 CHAR), 
	MIDDLE_NAME VARCHAR2(255 CHAR), 
	OCCUPATION VARCHAR2(255 CHAR), 
	REQUEST_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (PERSONAL_INFO_ID));
     commit;
     
   CREATE SEQUENCE  QCIB_EQ_E_PRSNL_INFO_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
     
      CREATE TABLE QCIB_EQ_E_PHONE_INFO 
   (	PHONE_INFO_ID NUMBER(19,0) NOT NULL ENABLE, 
	PHONE_NUMBER VARCHAR2(255 CHAR), 
	REPORTED_DATA VARCHAR2(255 CHAR), 
	TYPE_CODE VARCHAR2(255 CHAR), 
	REQUEST_UNIQUE_ID NUMBER(19,0), 
	SEQ VARCHAR2(20 BYTE), 
	 PRIMARY KEY (PHONE_INFO_ID));
     commit;
   CREATE SEQUENCE  QCIB_EQ_E_PHONE_INFO_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
     
     
     CREATE TABLE QCIB_EQ_E_VOTER_REQ
   (	VOTER_REQUEST_ID NUMBER(19,0) NOT NULL ENABLE, 
	SOURCE VARCHAR2(255 CHAR), 
	VOTER_ID VARCHAR2(255 CHAR), 
	REQUEST_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (VOTER_REQUEST_ID));
     COMMIT;
   
   CREATE SEQUENCE  QCIB_EQ_E_VOTER_REQ_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
     
     
      CREATE TABLE QCIB_EQ_E_VOTER_RES 
   (	VOTER_RESPONSE_ID NUMBER(19,0) NOT NULL ENABLE, 
	RETURN_CODE VARCHAR2(255 CHAR), 
	TITLE VARCHAR2(255 CHAR), 
	VOTER VARCHAR2(255 CHAR), 
	REQUEST_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (VOTER_RESPONSE_ID));
     commit;
     
     
   CREATE SEQUENCE  QCIB_EQ_E_VOTER_RES_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
 
    CREATE SEQUENCE  QCIB_EQ_E_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
     
     
     
     -----QCIB_EQ_M Table---
     
     CREATE TABLE QCIB_EQ_M_ACNT_DTLS
   (	ACCOUNT_ID NUMBER(19,0) NOT NULL ENABLE, 
	ACCOUNT_DETAIL_ID VARCHAR2(255 CHAR), 
	ACCOUNT_NUMBER VARCHAR2(255 CHAR), 
	ACCOUNT_STATUS VARCHAR2(255 CHAR), 
	ADDITIONAL_MFI_DTLS CLOB DEFAULT EMPTY_CLOB(), 
	APPLIED_AMOUNT VARCHAR2(255 CHAR), 
	BRANCH_ID_MFI VARCHAR2(255 CHAR), 
	CURRENT_BALANCE VARCHAR2(255 CHAR), 
	DATE_CLOSED VARCHAR2(255 CHAR), 
	DATE_OPENED VARCHAR2(255 CHAR), 
	DATE_REPORTED VARCHAR2(255 CHAR), 
	DATE_SANCTIONED VARCHAR2(255 CHAR), 
	DAYS_PAST_DUE VARCHAR2(255 CHAR), 
	DISBURSED_AMMOUNT VARCHAR2(255 CHAR), 
	HISTORY_24_MONTHS CLOB DEFAULT EMPTY_CLOB(), 
	INSTALLMENT_AMOUNT VARCHAR2(255 CHAR), 
	INSTITUTION VARCHAR2(255 CHAR), 
	KENDRA_AID_MFI VARCHAR2(255 CHAR), 
	KEY_PERSON CLOB DEFAULT EMPTY_CLOB(), 
	LOAN_CATEGORY VARCHAR2(255 CHAR), 
	LOAN_CYCLE_ID VARCHAR2(255 CHAR), 
	LOAN_PURPOSE VARCHAR2(255 CHAR), 
	NO_OF_INSTALLMENTS VARCHAR2(255 CHAR), 
	NOMINEE CLOB DEFAULT EMPTY_CLOB(), 
	REPAYMENT_TENURE VARCHAR2(255 CHAR), 
	REPORTED_DATE VARCHAR2(255 CHAR), 
	SANCTION_AMOUNT VARCHAR2(255 CHAR), 
	SEQUENCE VARCHAR2(255 CHAR), 
	REQUEST_UNIQUE_ID NUMBER(19,0)
   );
   COMMIT;
   
   CREATE SEQUENCE  QCIB_EQ_M_ACNT_DTLS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
   CREATE SEQUENCE  QCIB_EQ_M_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
   
   
   CREATE TABLE QCIB_EQ_M_ACNT_SMMRY 
   (	ACCOUNT_ID NUMBER NOT NULL ENABLE, 
	REQUEST_UNIQUE_ID NUMBER, 
	TOTAL_BALANCE_AMOUNT VARCHAR2(255 BYTE), 
	NO_OF_PAST_DUE_ACCOUNTS VARCHAR2(255 BYTE), 
	TOTAL_MONTHLY_PAYMENT_AMOUNT VARCHAR2(255 BYTE), 
	TOTAL_PAST_DUE VARCHAR2(255 BYTE), 
	NO_OF_ACTIVE_ACCOUNT VARCHAR2(255 BYTE), 
	TOTAL_WRITTEN_OFF_ACCOUNT VARCHAR2(255 BYTE), 
	 PRIMARY KEY (ACCOUNT_ID));
     COMMIT;
     
   CREATE SEQUENCE  QCIB_EQ_M_ACNT_SMMRY_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
    
      CREATE TABLE QCIB_EQ_M_DTLS_LOGS
   (	REQUEST_UNIQUE_ID NUMBER NOT NULL ENABLE, 
	TRACKER_ID VARCHAR2(255 BYTE), 
	CREATED_TIME TIMESTAMP (6), 
	UPDATED_TIME TIMESTAMP (6), 
	RESPONSE_XML CLOB DEFAULT EMPTY_CLOB(), 
	REQUEST_XML CLOB DEFAULT EMPTY_CLOB(), 
	REQUEST_INFO_JSON CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_HEADER_INFO_JSON CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_ERROR_INFO_JSON CLOB DEFAULT EMPTY_CLOB(), 
	MISCELLANEOUS_INFO_JSON CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_ID_CONTACT_INFO_JSON CLOB DEFAULT EMPTY_CLOB(), 
	REMARKS VARCHAR2(255 CHAR), 
	FINAL_HTML_DATA CLOB DEFAULT EMPTY_CLOB(), 
	BYTE_ARRAY_STRING CLOB DEFAULT EMPTY_CLOB(), 
	DISCLAIMER CLOB DEFAULT EMPTY_CLOB(), 
	REQUEST_JSON CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_JSON CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_DATE VARCHAR2(30 BYTE), 
	RESPONSE_TIME VARCHAR2(30 BYTE), 
	RESPONSE_ORDER_NUM VARCHAR2(30 BYTE)
   );
   COMMIT;
   
   CREATE SEQUENCE  QCIB_EQ_M_DTLS_LOGS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
  
   
    CREATE TABLE QCIB_EQ_M_ENQRS 
   (	INQUIRY_ID NUMBER NOT NULL ENABLE, 
	REQUEST_UNIQUE_ID NUMBER, 
	INQUIRY_TIME VARCHAR2(255 BYTE), 
	SEQUENCE VARCHAR2(255 BYTE), 
	REQUEST_PURPOSE VARCHAR2(255 BYTE), 
	INQUIRY_DATE VARCHAR2(255 BYTE), 
	INSTITUTION VARCHAR2(255 BYTE), 
	 PRIMARY KEY (INQUIRY_ID));
     COMMIT;
     
     CREATE SEQUENCE  QCIB_EQ_M_ENQRS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
     
      CREATE TABLE QCIB_EQ_M_ID_CNTCT_DTLS 
   (	REQUEST_UNIQUE_ID VARCHAR2(50 BYTE), 
	IDENTITY_SEQ VARCHAR2(50 BYTE), 
	ID_NUMBER VARCHAR2(50 BYTE), 
	REPORTED_DATE VARCHAR2(50 BYTE), 
	MARITAL_STATUS VARCHAR2(50 BYTE), 
	AGE VARCHAR2(50 BYTE), 
	FIRSTNAME VARCHAR2(100 BYTE), 
	MIDDLENAME VARCHAR2(100 BYTE), 
	LASTNAME VARCHAR2(100 BYTE), 
	ADDITIONAL_MD_NAME VARCHAR2(100 BYTE), 
	GENDER VARCHAR2(10 BYTE), 
	DATEOFBIRTH VARCHAR2(30 BYTE), 
	ALIASNAME VARCHAR2(50 BYTE), 
	NOOFDEPENDENTS VARCHAR2(50 BYTE), 
	ADDITIONALNAMETYPE VARCHAR2(50 BYTE), 
	ADDITIONALNAME VARCHAR2(100 BYTE), 
	ADDRESS VARCHAR2(1000 BYTE), 
	CONTACT_SEQ VARCHAR2(50 BYTE), 
	STATE VARCHAR2(100 BYTE), 
	TYPE VARCHAR2(50 BYTE)
   ) ;
   COMMIT;
   CREATE SEQUENCE  QCIB_EQ_M_ID_CNTCT_DTLS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
   
   CREATE TABLE QCIB_EQ_M_INCM_DTLS 
   (	INCOME_DTLS_ID NUMBER NOT NULL ENABLE, 
	REQUEST_UNIQUE_ID NUMBER, 
	MONTHLY_INCOME VARCHAR2(255 BYTE), 
	OCCUPATION VARCHAR2(255 BYTE), 
	MONTHLY_EXPENSE VARCHAR2(255 BYTE), 
	REPORTED_DATE VARCHAR2(255 BYTE), 
	SEQUENCE VARCHAR2(255 BYTE), 
	 PRIMARY KEY (INCOME_DTLS_ID));
     COMMIT;
     
     CREATE SEQUENCE  QCIB_EQ_M_INCM_DTLS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
	 
	  CREATE TABLE QCIB_EQ_M_ADRS_DTLS 
   (	addressDetail_id NUMBER(19,0) NOT NULL ENABLE, 
	ADDRESS VARCHAR2(255 CHAR), 
	SEQ VARCHAR2(25 CHAR), 
	REPORTED_DATE VARCHAR2(255 CHAR), 
	STATE VARCHAR2(255 CHAR), 
	TYPE VARCHAR2(255 CHAR), 
	REQUEST_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (addressDetail_id));
     commit;
      CREATE SEQUENCE  QCIB_EQ_M_ADRS_DTLS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
     commit;
     CREATE SEQUENCE  QCIB_EQ_M_Identity_DTLS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
     commit;
     CREATE SEQUENCE  QCIB_EQ_M_FAMILY_DTLS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
     commit;
     CREATE SEQUENCE  QCIB_EQ_M_PRSNL_DTLS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
     commit;
	 CREATE SEQUENCE  QCIB_EQ_M_24MONTH_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
     commit;
     CREATE SEQUENCE  QCIB_EQ_M_ADDIMFI_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
     commit;
     CREATE SEQUENCE  QCIB_EQ_M_ADMFIADR_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
     commit;
     CREATE SEQUENCE  QCIB_EQ_M_ADMFIID_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
     commit;
    CREATE SEQUENCE  QCIB_EQ_M_NOMINEE_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
     commit;
     CREATE SEQUENCE  QCIB_EQ_M_KYPRSN_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
     commit;
	 
	  CREATE TABLE QCIB_EQ_M_24MONTH 
   (	historyMonthId NUMBER(19,0) NOT NULL ENABLE, 
	paymentstatus VARCHAR2(255 CHAR), 
	KEY VARCHAR2(25 CHAR), 
	REQUEST_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (historyMonthId));
     commit;
     
CREATE TABLE QCIB_EQ_M_ADDI_MFI_DTL 
   (	AD_MFI_UNIQUE_ID NUMBER(19,0) NOT NULL ENABLE, 
	id VARCHAR2(50 CHAR), 
	memberid VARCHAR2(50 CHAR), 
    mficlientfullname VARCHAR2(250 CHAR),
    mfigender VARCHAR2(25 CHAR),
    mfidob  VARCHAR2(25 CHAR),
	REQUEST_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (AD_MFI_UNIQUE_ID));
     commit;
     
     
CREATE TABLE QCIB_EQ_M_ADMFI_ADRS 
   (	MfiAddressId NUMBER(19,0) NOT NULL ENABLE, 
	mfistate VARCHAR2(100 CHAR), 
	seq VARCHAR2(50 CHAR), 
    mfiaddressline VARCHAR2(500 CHAR),
    mfipostalpin VARCHAR2(25 CHAR),
	REQUEST_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (MfiAddressId));
     commit;
     
CREATE TABLE QCIB_EQ_M_ADMFI_ID 
   (	addionalMfiId NUMBER(19,0) NOT NULL ENABLE, 
	mfiOtherId VARCHAR2(100 CHAR), 
	REQUEST_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (addionalMfiId));
     commit;
     
     
     CREATE TABLE QCIB_EQ_M_NOMINEE 
   (	nomineeId NUMBER(19,0) NOT NULL ENABLE, 
	relationtype VARCHAR2(250 CHAR), 
    name VARCHAR2(250 CHAR),
    REQUEST_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (nomineeId));
     commit;
     
     CREATE TABLE QCIB_EQ_M_KEY_PERSON 
   (	keyPersonId NUMBER(19,0) NOT NULL ENABLE, 
	relationtype VARCHAR2(250 CHAR), 
    name VARCHAR2(250 CHAR), 
	REQUEST_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (keyPersonId));
     commit;
	 
      CREATE TABLE QCIB_EQ_M_Identity_DTLS 
   (	IDENTITY_INFO_ID NUMBER(19,0) NOT NULL ENABLE, 
    	idnumber VARCHAR2(255 CHAR), 
        seq VARCHAR2(25 CHAR), 
        REPORTED_DATE VARCHAR2(255 CHAR), 
        REQUEST_UNIQUE_ID NUMBER(19,0), 
        PRIMARY KEY (IDENTITY_INFO_ID));
        commit;
        
          CREATE TABLE QCIB_EQ_M_FAMILY_DTLS 
   (	FAMILYDETAIL_ID NUMBER(19,0) NOT NULL ENABLE, 
    	noofdependents VARCHAR2(255 CHAR), 
        additionalnametype VARCHAR2(255 CHAR), 
        additionalname VARCHAR2(255 CHAR), 
        REQUEST_UNIQUE_ID NUMBER(19,0), 
        PRIMARY KEY (FAMILYDETAIL_ID));
        commit;
     
     CREATE TABLE QCIB_EQ_M_PRSNL_DTLS 
   (	PERSONALDETAIL_ID NUMBER NOT NULL ENABLE, 
	MARITAL_STATUS VARCHAR2(20 BYTE), 
	AGE VARCHAR2(10 BYTE), 
	FIRSTNAME VARCHAR2(50 BYTE), 
	MIDDLENAME VARCHAR2(50 BYTE), 
	LASTNAME VARCHAR2(50 BYTE), 
    additionalmiddlename VARCHAR2(50 BYTE), 
	GENDER VARCHAR2(20 BYTE), 
	DATEOFBIRTH VARCHAR2(20 BYTE), 
    aliasname VARCHAR2(255 BYTE), 
    REPORTED_DATE VARCHAR2(255 CHAR), 
	REQUEST_UNIQUE_ID NUMBER,
     PRIMARY KEY (PERSONALDETAIL_ID)
   );
   commit;
   
   
	 
	 
     --QCIB_EQ_P Table--
     
      CREATE TABLE QCIB_EQ_P_ACNT_DTLS 
   (	REASON VARCHAR2(255 BYTE), 
	GETLAST_PAYMENT VARCHAR2(255 BYTE), 
	REPORTED_DATE VARCHAR2(255 BYTE), 
	ACCOUNT_TYPE VARCHAR2(255 BYTE), 
	INSTITUTION VARCHAR2(255 BYTE), 
	COLLATERAL_TYPE VARCHAR2(255 BYTE), 
	INTERESTRATE VARCHAR2(255 BYTE), 
	BALANCE VARCHAR2(255 BYTE), 
	TERM_FREQUENCY VARCHAR2(255 BYTE), 
	ACCOUNT_STATUS VARCHAR2(255 BYTE), 
	HISTORY_FOURTY_EIGHT_MONTHS CLOB DEFAULT EMPTY_CLOB(), 
	SEQUENCE VARCHAR2(255 BYTE), 
	INSTALLMENT_AMOUNT VARCHAR2(255 BYTE), 
	SANCTION_AMOUNT VARCHAR2(255 BYTE), 
	REPAYMENT_TENURE VARCHAR2(255 BYTE), 
	ACCOUNT_NUMBER VARCHAR2(255 BYTE), 
	DISPUTE_CODE VARCHAR2(255 BYTE), 
	OWNER_SHIP_TYPE VARCHAR2(255 BYTE), 
	SUIT_FILED_STATUS VARCHAR2(255 BYTE), 
	COLLATERAL_VALUE VARCHAR2(255 BYTE), 
	OPEN VARCHAR2(255 BYTE), 
	ASSET_CLASSIFICATION VARCHAR2(255 BYTE), 
	ACCOUNT_DETAIL_ID NUMBER NOT NULL ENABLE, 
	REQUEST_UNIQUE_ID NUMBER, 
	 PRIMARY KEY (ACCOUNT_DETAIL_ID));
     COMMIT;
     
	 CREATE SEQUENCE  QCIB_EQ_P_ACNT_DTLS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
     
     	 CREATE SEQUENCE  QCIB_EQ_P_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
     
      CREATE TABLE QCIB_EQ_P_ACNT_SMRY 
   (	OLDEST_ACCOUNT VARCHAR2(255 BYTE), 
	NO_OF_ZERO_BALANCE_ACCOUNT VARCHAR2(255 BYTE), 
	NO_OF_ACCOUNTS VARCHAR2(255 BYTE), 
	TOTAL_SANCTION_AMOUNT VARCHAR2(255 BYTE), 
	TOTAL_CREDIT_LIMIT VARCHAR2(255 BYTE), 
	RECENT_ACCOUNT VARCHAR2(255 BYTE), 
	SINGLE_HIGHEST_SANCTION_AMOUNT VARCHAR2(255 BYTE), 
	TOTAL_HIGH_CREDIT VARCHAR2(255 BYTE), 
	TOTAL_BALANCE_AMOUNT VARCHAR2(255 BYTE), 
	NO_OF_WRITE_OFFS VARCHAR2(255 BYTE), 
	NO_OF_PAST_DUE_ACCOUNT VARCHAR2(255 BYTE), 
	AVERAGE_OPEN_BALANCE VARCHAR2(255 BYTE), 
	TOTAL_MONTHLY_PAYMENT_AMOUNT VARCHAR2(255 BYTE), 
	TOTAL_PAST_DUE VARCHAR2(255 BYTE), 
	SINGLE_HIGHEST_BALANCE VARCHAR2(255 BYTE), 
	SINGLE_HIGHEST_CREDIT VARCHAR2(255 BYTE), 
	NO_OF_ACTIVE_ACCOUNTS VARCHAR2(255 BYTE), 
	ACCOUNT_ID NUMBER NOT NULL ENABLE, 
	REQUEST_UNIQUE_ID NUMBER, 
	 PRIMARY KEY (ACCOUNT_ID));
     COMMIT;
     
     CREATE SEQUENCE  QCIB_EQ_P_ACNT_SMRY_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  
     
      CREATE TABLE QCIB_EQ_P_ADRS_DTLS 
   (	ADDRESSDETAIL_ID NUMBER NOT NULL ENABLE, 
	ADDRESS VARCHAR2(500 BYTE), 
	POSTAL VARCHAR2(20 BYTE), 
	SEQ VARCHAR2(20 BYTE), 
	STATE VARCHAR2(100 BYTE), 
	TYPE VARCHAR2(50 BYTE), 
	REPORTEDDATE VARCHAR2(100 BYTE), 
	REQUEST_UNIQUE_ID NUMBER
   );
   COMMIT;
   CREATE SEQUENCE  QCIB_EQ_P_ADRS_DTLS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
   
   
    CREATE TABLE QCIB_EQ_P_DTLS_LOGS
   (	REQUEST_UNIQUE_ID NUMBER NOT NULL ENABLE, 
	TRACKER_ID VARCHAR2(255 BYTE), 
	CREATED_TIME TIMESTAMP (6), 
	UPDATED_TIME TIMESTAMP (6), 
	RESPONSE_XML CLOB DEFAULT EMPTY_CLOB(), 
	REQUEST_XML CLOB DEFAULT EMPTY_CLOB(), 
	REQUEST_INFO_JSON CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_HEADER_INFO_JSON CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_ERROR_INFO_JSON CLOB DEFAULT EMPTY_CLOB(), 
	MISCELLANEOUS_INFO_JSON CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_ID_CONTACT_INFO_JSON CLOB DEFAULT EMPTY_CLOB(), 
	SCORE_JSON CLOB DEFAULT EMPTY_CLOB(), 
	REMARKS VARCHAR2(255 CHAR), 
	FINAL_HTML_DATA CLOB DEFAULT EMPTY_CLOB(), 
	BYTE_ARRAY_STRING CLOB DEFAULT EMPTY_CLOB(), 
	DISCLAIMER CLOB DEFAULT EMPTY_CLOB(), 
	REQUEST_JSON CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_JSON CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_DATE VARCHAR2(30 BYTE), 
	RESPONSE_TIME VARCHAR2(30 BYTE), 
	RESPONSE_ORDER_NUM VARCHAR2(30 BYTE)
   );
   COMMIT;
   CREATE SEQUENCE  QCIB_EQ_P_DTLS_LOGS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
   
    CREATE TABLE QCIB_EQ_P_EMAIL_DTLS 
   (	EMAILDETAILS_ID NUMBER NOT NULL ENABLE, 
	SEQ VARCHAR2(50 BYTE), 
	EMAILADDRESS VARCHAR2(200 BYTE), 
	REPORTEDDATE VARCHAR2(20 BYTE), 
	REQUEST_UNIQUE_ID NUMBER
   );
   COMMIT;
   CREATE SEQUENCE  QCIB_EQ_P_EMAIL_DTLS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
   
    CREATE TABLE QCIB_EQ_P_ENQRS 
   (	ENQUIRY_DATE VARCHAR2(255 BYTE), 
	INSTITUTION VARCHAR2(255 BYTE), 
	ENQUIRY_TIME VARCHAR2(255 BYTE), 
	ENQUIRY_SEQ VARCHAR2(255 BYTE), 
	REQUEST_PURPOSE VARCHAR2(255 BYTE), 
	INQUIRY_ID NUMBER NOT NULL ENABLE, 
	REQUEST_UNIQUE_ID NUMBER, 
	 PRIMARY KEY (INQUIRY_ID));
     COMMIT;
     CREATE SEQUENCE  QCIB_EQ_P_ENQRS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
     
      CREATE TABLE QCIB_EQ_P_ENQ_SMRY 
   (	PAST_THIRTY_DAYS VARCHAR2(255 BYTE), 
	TOTAL VARCHAR2(255 BYTE), 
	PURPOSE VARCHAR2(255 BYTE), 
	PAST_TWELVE_MONTH VARCHAR2(255 BYTE), 
	PAST_TWENTY_FOUR_MONTH VARCHAR2(255 BYTE), 
	RECENT VARCHAR2(255 BYTE), 
	ENQUIRY_ID NUMBER NOT NULL ENABLE, 
	REQUEST_UNIQUE_ID NUMBER, 
	 PRIMARY KEY (ENQUIRY_ID));
     COMMIT;
     CREATE SEQUENCE  QCIB_EQ_P_ENQ_SMRY_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
     
     
     CREATE TABLE QCIB_EQ_P_OTHER 
   (	ALL_LINE_SEVER_WRITTEN VARCHAR2(255 BYTE), 
	SEVER_WRITTEN_IN_SIX_MONTH VARCHAR2(255 BYTE), 
	NUMBER_OF_OPEN_TRADES VARCHAR2(255 BYTE), 
	AGE_OF_OLDEST_TRADE VARCHAR2(255 BYTE), 
	SEVER_WRITTEN_IN_NINE_MONTH VARCHAR2(255 BYTE), 
	OTHER_ID NUMBER NOT NULL ENABLE, 
	REQUEST_UNIQUE_ID NUMBER, 
	 PRIMARY KEY (OTHER_ID));
     COMMIT;
     CREATE SEQUENCE  QCIB_EQ_P_OTHER_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  commit;
     
     CREATE TABLE QCIB_EQ_P_PAN_DTLS 
   (	PANDETAIL_ID NUMBER NOT NULL ENABLE, 
	SEQ VARCHAR2(20 BYTE), 
	PANNUMBER VARCHAR2(20 BYTE), 
	REPORTEDDATE VARCHAR2(100 BYTE), 
	REQUEST_UNIQUE_ID NUMBER
   ) ;
   COMMIT;
   CREATE SEQUENCE  QCIB_EQ_P_PAN_DTLS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
 COMMIT;

   
   CREATE TABLE QCIB_EQ_P_PRSNL_DTLS 
   (	PERSONALDETAIL_ID NUMBER NOT NULL ENABLE, 
	TOTALINCOME VARCHAR2(20 BYTE), 
	OCCUPATION VARCHAR2(100 BYTE), 
	AGE VARCHAR2(10 BYTE), 
	FIRSTNAME VARCHAR2(50 BYTE), 
	MIDDLENAME VARCHAR2(50 BYTE), 
	LASTNAME VARCHAR2(50 BYTE), 
	GENDER VARCHAR2(20 BYTE), 
	DATEOFBIRTH VARCHAR2(20 BYTE), 
	REQUEST_UNIQUE_ID NUMBER
   );
   COMMIT;
   CREATE SEQUENCE  QCIB_EQ_P_PRSNL_DTLS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
   COMMIT;

  
   CREATE TABLE QCIB_EQ_P_PHN_DTLS 
   (	PHONEDETAIL_ID NUMBER NOT NULL ENABLE, 
	SEQ VARCHAR2(20 BYTE), 
	TYPE_CODE VARCHAR2(50 BYTE), 
	PHONENUMBER VARCHAR2(20 BYTE), 
	REPORTEDDATE VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	REQUEST_UNIQUE_ID NUMBER
   );
   COMMIT;
   CREATE SEQUENCE  QCIB_EQ_P_PHN_DTLS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  COMMIT;
   
    CREATE TABLE QCIB_EQ_P_RCNT_ACTVTY 
   (	TOTAL_ENQUIRIES VARCHAR2(255 BYTE), 
	ACCOUNTS_UPDATED VARCHAR2(255 BYTE), 
	ACCOUNTS_DELIQUENT VARCHAR2(255 BYTE), 
	ACCOUNTS_OPENED VARCHAR2(255 BYTE), 
	ACTIVITY_ID NUMBER NOT NULL ENABLE, 
	REQUEST_UNIQUE_ID NUMBER, 
	 PRIMARY KEY (ACTIVITY_ID));
     COMMIT;
     CREATE SEQUENCE  QCIB_EQ_P_RCNT_ACTVTY_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  COMMIT;
     
     CREATE TABLE QCIB_EQ_P_SCOR_ELMNTS 
   (	CODE VARCHAR2(255 BYTE), 
	DESCRIPTION VARCHAR2(255 BYTE), 
	TYPE VARCHAR2(255 BYTE), 
	SEQUENCE VARCHAR2(255 BYTE), 
	SCORING_ELEMENT_KEY NUMBER NOT NULL ENABLE, 
	REQUEST_UNIQUE_ID NUMBER, 
	 PRIMARY KEY (SCORING_ELEMENT_KEY));
     CREATE SEQUENCE  QCIB_EQ_P_SCOR_ELMNTS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  COMMIT;
  
     CREATE TABLE QCIB_EQ_P_VOTER_DTLS 
   (	VOTERDETAIL_ID NUMBER NOT NULL ENABLE, 
	SEQ VARCHAR2(20 BYTE), 
	REQUEST_UNIQUE_ID NUMBER, 
	VOTERIDNUM VARCHAR2(20 BYTE), 
	REPORTEDDATE VARCHAR2(100 BYTE)
   ) ;
   COMMIT;
CREATE SEQUENCE  QCIB_EQ_P_VOTER_DTLS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
  COMMIT;
  
	 
   --EQUIFAX VID Table---
   
   CREATE TABLE QCIB_EQ_V_DTLS_LOGS 
   (	REQUEST_UNIQUE_ID NUMBER(19,0) NOT NULL ENABLE, 
	CREATED_TIME TIMESTAMP (6), 
	DISCLAIMER CLOB DEFAULT EMPTY_CLOB(), 
	FIRST_NAME VARCHAR2(255 CHAR), 
	IDENTITIFICATION_ID VARCHAR2(255 CHAR), 
	LAST_NAME VARCHAR2(255 CHAR), 
	LAST_UPDATED_DATE VARCHAR2(255 CHAR), 
	NSDL_RESPONSE_ID VARCHAR2(255 CHAR), 
	PERCENTAGE_MATCH VARCHAR2(255 CHAR), 
	REMARKS VARCHAR2(255 CHAR), 
	REQUEST_INFO_JSON CLOB DEFAULT EMPTY_CLOB(), 
	REQUEST_XML CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_DATE VARCHAR2(255 CHAR), 
	RESPONSE_ERROR_INFO_JSON CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_HEADER_INFO_JSON CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_ORDER_NO CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_TIME VARCHAR2(255 CHAR), 
	RESPONSE_XML CLOB DEFAULT EMPTY_CLOB(), 
	RETURN_CODE VARCHAR2(255 CHAR), 
	RETURN_CODE_DESCRIPTION VARCHAR2(255 CHAR), 
	TITLE VARCHAR2(255 CHAR), 
	TRACKER_ID VARCHAR2(255 CHAR), 
	UPDATED_TIME TIMESTAMP (6), 
	REQUEST_JSON CLOB DEFAULT EMPTY_CLOB(), 
	RESPONSE_JSON CLOB DEFAULT EMPTY_CLOB(), 
	HTMLDATA CLOB DEFAULT EMPTY_CLOB(), 
	PDF_BYTE_ARRAY CLOB DEFAULT EMPTY_CLOB(), 
	 PRIMARY KEY (REQUEST_UNIQUE_ID));
     COMMIT;
	 
    CREATE SEQUENCE  QCIB_EQ_V_DTLS_LOGS_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
   COMMIT;
   

----------------------------------update on 15 sept---------------------

    CREATE TABLE QCIB_EQ_M_OTHER_KEY_IND(
    otherKeyId NUMBER(19,0) NOT NULL ENABLE, 
	 ageOfOldestTrade varchar2(100),
	 numberOfOpenTrades varchar2(100),
	 allLinesEVERWritten varchar2(100),
	 allLinesEVERWrittenIn9Months varchar2(100),
	 allLinesEVERWrittenIn6Months varchar2(100),
     REQUEST_UNIQUE_ID NUMBER(19,0),
     PRIMARY KEY (otherKeyId));
    COMMIT;
    
        CREATE TABLE QCIB_EQ_P_OTHER_KEY_IND(
        otherKeyId NUMBER(19,0) NOT NULL ENABLE, 
     ageOfOldestTrade varchar2(100),
	 numberOfOpenTrades varchar2(100),
	 allLinesEVERWritten varchar2(100),
	 allLinesEVERWrittenIn9Months varchar2(100),
	 allLinesEVERWrittenIn6Months varchar2(100),
        REQUEST_UNIQUE_ID NUMBER(19,0),
        PRIMARY KEY (otherKeyId));
    COMMIT;
    
    
 CREATE SEQUENCE  QCIB_EQ_M_OTHER_KEY_IND_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
   commit;
    
 CREATE SEQUENCE  QCIB_EQ_P_OTHER_KEY_IND_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
   commit;
   
 ALTER TABLE QCIB_EQ_M_ENQRS add (ACCOUNT varchar2(100));
commit;
 ALTER TABLE QCIB_EQ_P_ENQRS add (ACCOUNT varchar2(100));
commit;
alter table QCIB_EQ_M_PRSNL_DTLS add(Suffix varchar2(50));
commit;
alter table QCIB_EQ_P_PRSNL_DTLS add(Suffix varchar2(50));
commit;

----------------------------------update on 15 sept end---------------------  

--------------------------update on 19 sept ------------------------



ALTER TABLE QCIB_EQ_P_ACNT_DTLS add(Past_Due_Amount varchar2(20),
Other_Last_Payment varchar2(20),Last_Payment_Date varchar2(50),High_Credit varchar2(20),
Individual_write_Off_Amnt varchar(20),Credit_Limit varchar2(50),Date_Closed varchar2(50),Date_Opened varchar2(50)
);
commit;
ALTER TABLE  QCIB_EQ_P_DTLS_LOGS add(
Member_Ref_Number varchar2(50),member_id varchar2(50)
);
commit;

ALTER TABLE  QCIB_EQ_M_DTLS_LOGS add(
Member_Ref_Number varchar2(50),member_id varchar2(50)
);
commit;


ALTER TABLE  QCIB_EQ_M_24MONTH add(
suitfiled_status varchar2(50),asset_classification_status varchar2(50)
);
commit;

CREATE TABLE QCIB_EQ_P_48MONTH 
(	
   REQUEST_UNIQUE_ID NUMBER(19,0), 
	KEY VARCHAR2(25 CHAR), 
    paymentstatus VARCHAR2(255 CHAR), 
    suitfiled_status varchar2(50),
    asset_classification_status varchar2(50)
);
commit;
	 
ALTER TABLE  QCIB_EQ_M_24MONTH add(suitfiled_status varchar2(50),asset_classification_status varchar2(50));
commit;

alter table QCIB_EQ_M_ENQRS rename column ACCOUNT to AMOUNT;
alter table QCIB_EQ_P_ENQRS rename column ACCOUNT to AMOUNT;
commit;

Alter table QCIB_EQ_M_ACNT_SMMRY add(
OLDEST_ACCOUNT	VARCHAR2(255 BYTE),
NO_OF_ZERO_BALANCE_ACCOUNT	VARCHAR2(255 BYTE),
NO_OF_ACCOUNTS	VARCHAR2(255 BYTE),
TOTAL_SANCTION_AMOUNT	VARCHAR2(255 BYTE),
TOTAL_CREDIT_LIMIT	VARCHAR2(255 BYTE),
RECENT_ACCOUNT	VARCHAR2(255 BYTE),
SINGLE_HIGHEST_SANCTION_AMOUNT	VARCHAR2(255 BYTE),
TOTAL_HIGH_CREDIT	VARCHAR2(255 BYTE),
NO_OF_WRITE_OFFS	VARCHAR2(255 BYTE),
AVERAGE_OPEN_BALANCE	VARCHAR2(255 BYTE),
SINGLE_HIGHEST_BALANCE	VARCHAR2(255 BYTE),
SINGLE_HIGHEST_CREDIT	VARCHAR2(255 BYTE)
);
commit;

ALTER TABLE QCIB_EQ_P_PRSNL_DTLS ADD(
ADDITIONALMIDDLENAME VARCHAR2(255),
ALIASNAMEINFO VARCHAR2(255)
);
COMMIT;


CREATE TABLE QCIB_EQ_P_PREV_NAMES 
(	
   REQUEST_UNIQUE_ID NUMBER(19,0), 
	FIRSTNAME VARCHAR2(25 CHAR), 
    MIDDLENAME VARCHAR2(255 CHAR), 
    LASTNAME varchar2(50),
    Suffix varchar2(50),
    ADDITIONALMIDDLENAME varchar2(250)
);
commit;


ALTER TABLE QCIB_EQ_M_IDENTITY_DTLS DROP COLUMN SEQ;
ALTER TABLE QCIB_EQ_M_IDENTITY_DTLS aDD( ID_TYPE VARCHAR2(50));
commit;
---------------------------update on 19 sept end------------------------------------
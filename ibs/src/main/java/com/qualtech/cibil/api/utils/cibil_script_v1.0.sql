CREATE TABLE QCIB_CIBILRESPONSEENTITY 
   (	CIBIL_UNIQUE_ID NUMBER(19,0) NOT NULL ENABLE, 
	COREALTIONID VARCHAR2(255 CHAR), 
	DATEOFBIRTH VARCHAR2(255 CHAR), 
	ENQUIRYCUMBER VARCHAR2(255 CHAR), 
	GENDER VARCHAR2(255 CHAR), 
	NAME1 VARCHAR2(255 CHAR), 
	NAME2 VARCHAR2(255 CHAR), 
	NAME3 VARCHAR2(255 CHAR), 
	NAME4 VARCHAR2(255 CHAR), 
	NAME5 VARCHAR2(255 CHAR), 
	PROCESSEDDATE VARCHAR2(255 CHAR), 
	RETURNCODE VARCHAR2(255 CHAR), 
	SCORE VARCHAR2(255 CHAR), 
	SCORECARDNAME VARCHAR2(255 CHAR), 
	SCOREDATE VARCHAR2(255 CHAR), 
	TIMEPROCEESED VARCHAR2(255 CHAR), 
	MEMBERID VARCHAR2(100 BYTE), 
	MEMBERREFNO VARCHAR2(100 BYTE), 
	LENOFTRANSMISSION VARCHAR2(50 BYTE), 
	PRINTABLEREPORT VARCHAR2(4000 BYTE), 
	PDFBYTEARRAY VARCHAR2(4000 BYTE), 
        PRIMARY KEY (CIBIL_UNIQUE_ID)
  );
 commit;

  CREATE TABLE QCIB_CIBILTELEPHONE 
   (	TELEPHONEID NUMBER(19,0) NOT NULL ENABLE, 
	TELEPHONEEXTN VARCHAR2(255 CHAR), 
	TELEPHONENUMBER VARCHAR2(255 CHAR), 
	TELEPHONETYPE VARCHAR2(255 CHAR), 
	CIBIL_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (TELEPHONEID)
 );
 commit;




  CREATE TABLE QCIB_CIBILADDRESSDETAILS 
   (	ADDRESS_ID NUMBER(19,0) NOT NULL ENABLE, 
	ADDRESSCATEGORY VARCHAR2(255 CHAR), 
	ADDRESSLINE1 VARCHAR2(255 CHAR), 
	ADDRESSLINE2 VARCHAR2(255 CHAR), 
	ADDRESSLINE3 VARCHAR2(255 CHAR), 
	ADDRESSLINE4 VARCHAR2(255 CHAR), 
	ADDRESSLINE5 VARCHAR2(255 CHAR), 
	DATEREPORTED VARCHAR2(255 CHAR), 
	MEMBERNAME VARCHAR2(255 CHAR), 
	PINCODE VARCHAR2(255 CHAR), 
	RESEDENCECODE VARCHAR2(255 CHAR), 
	STATECODE VARCHAR2(255 CHAR), 
	CIBIL_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (ADDRESS_ID)
  );
 
commit;



  
  CREATE TABLE QCIB_CIBILACCOUNTDETAILS 
   (	ACCOUNTID NUMBER(19,0) NOT NULL ENABLE, 
	ACCOUNTGROUP VARCHAR2(255 CHAR), 
	AMOUNTOVERDUE VARCHAR2(255 CHAR), 
	CIBILREMARKSCODE VARCHAR2(255 CHAR), 
	DATECLOSE VARCHAR2(255 CHAR), 
	DATEOFEDISPUTEREMARKSCODE VARCHAR2(255 CHAR), 
	DATEOFEERRORCODE VARCHAR2(255 CHAR), 
	DATEOFEREMARKSCODE VARCHAR2(255 CHAR), 
	DATEOFLASTPAYMENT VARCHAR2(255 CHAR), 
	DATEOPENED VARCHAR2(255 CHAR), 
	DATEREPORTED VARCHAR2(255 CHAR), 
	DATEREPORTEDVERIFIED VARCHAR2(255 CHAR), 
	ERRORCODE VARCHAR2(255 CHAR), 
	PAYMENTHISTORY1 VARCHAR2(255 CHAR), 
	PAYMENTHISTORY2 VARCHAR2(255 CHAR), 
	PAYMENTHISTORYENDDATE VARCHAR2(255 CHAR), 
	PAYMENTHSTRSTARTDATE VARCHAR2(255 CHAR), 
	SUITFILLEDDEFAULT VARCHAR2(255 CHAR), 
	WRITTENOFFSTATUS VARCHAR2(255 CHAR), 
	CIBIL_UNIQUE_ID NUMBER(19,0), 
	ACCOUNTNUMBER VARCHAR2(255 CHAR), 
	ACCOUNTTYPE VARCHAR2(255 CHAR), 
	ACTUALPAYMENTAMOUNT VARCHAR2(255 CHAR), 
	CASHLIMIT VARCHAR2(255 CHAR), 
	CREDITLIMIT VARCHAR2(255 CHAR), 
	CURRNTBALANCE VARCHAR2(255 CHAR), 
	DATEOFENTRYFORCIBILREMARKSCODE VARCHAR2(255 CHAR), 
	DATEOFENTRYFORERRORCODE VARCHAR2(255 CHAR), 
	DATEOFFENTRYFORERRORCODE VARCHAR2(255 CHAR), 
	EMIAMOUNT VARCHAR2(255 CHAR), 
	OWNERSHIPINDICATOR VARCHAR2(255 CHAR), 
	PAYMENTFREQUENCY VARCHAR2(255 CHAR), 
	RATEOFINTEREST VARCHAR2(255 CHAR), 
	REPAYMENTTENURE VARCHAR2(255 CHAR), 
	REPORTINGMEMBERNAME VARCHAR2(255 CHAR), 
	SANCTIONAMOUNT VARCHAR2(255 CHAR), 
	SETTLEMENTAMOUNT VARCHAR2(255 CHAR), 
	VALUEOFCOLLETERAL VARCHAR2(255 CHAR), 
	WRITTENOFFAMOUNTPRINCIPAL VARCHAR2(255 CHAR), 
	WRITTENOFFAMOUNTTOTAL VARCHAR2(255 CHAR), 
	DATEOFENTRYOFDISPUTECODE VARCHAR2(255 CHAR), 
	ERRORDISPUTCODE2 VARCHAR2(255 CHAR), 
	ERRORDISPUTECODE1 VARCHAR2(255 CHAR), 
	 PRIMARY KEY (ACCOUNTID)
  );
 commit;




  CREATE TABLE QCIB_CIBILPAYMENTHISTORY 
   (	PAYMENTHISTORYID NUMBER(19,0) NOT NULL ENABLE, 
	ACCOUNTID NUMBER(19,0), 
	NDPDAC VARCHAR2(10 CHAR), 
	NDPDACMONYEAR VARCHAR2(10 CHAR), 
	CIBIL_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (PAYMENTHISTORYID)
  );
 commit;





  CREATE TABLE QCIB_CIBILEMPLOYMENTDETAIL 
   (	EMPLOYEEID NUMBER(19,0) NOT NULL ENABLE, 
	ACCOUNTTYPE VARCHAR2(255 CHAR), 
	CIBILREMARKSCODE VARCHAR2(255 CHAR), 
	DATEOFENTRYFORREMARKSCODE VARCHAR2(255 CHAR), 
	DATEREPORTEDANDCERTIFIED VARCHAR2(255 CHAR), 
	DATEDISPUTEREMARKSCODE VARCHAR2(255 CHAR), 
	ERRORCODE VARCHAR2(255 CHAR), 
	ERRORDATECODE VARCHAR2(255 CHAR), 
	GROSSSINCOMEINDICATOR VARCHAR2(255 CHAR), 
	INCOME VARCHAR2(255 CHAR), 
	MONTHLYINCOMEINDICATOR VARCHAR2(255 CHAR), 
	OCCUPATIONCODE VARCHAR2(255 CHAR), 
	REMARKSCODE1 VARCHAR2(255 CHAR), 
	REMARKSCODE2 VARCHAR2(255 CHAR), 
	CIBIL_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (EMPLOYEEID)
 );
 commit;



  
  CREATE TABLE QCIB_CIBILACCOUNTSUMMARY 
   (	SUMMARYID NUMBER(19,0) NOT NULL ENABLE, 
	ACCOUNTNUMBER VARCHAR2(255 CHAR), 
	ACCOUNTGROUP VARCHAR2(255 CHAR), 
	LIVECLOSEDINDICATOR VARCHAR2(255 CHAR), 
	CRHCRSANCTIONEDAMOUNT VARCHAR2(255 CHAR), 
	CURRENTBALANCE VARCHAR2(255 CHAR), 
	CIBIL_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (SUMMARYID)
  );
 
commit;
 


  CREATE TABLE QCIB_CIBILDISPUTEDETAILS 
   (	DISPUTEID NUMBER(19,0) NOT NULL ENABLE, 
	DISPUTEDATE VARCHAR2(255 CHAR), 
	REMARK VARCHAR2(255 CHAR), 
	CIBIL_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (DISPUTEID)
  );
 commit;



CREATE TABLE QCIB_CIBILEnquiryDetails 
   (	enquiryId NUMBER(19,0) NOT NULL ENABLE, 
	dateOfEnquiry VARCHAR2(255 CHAR), 
	enquiryShortName VARCHAR2(255 CHAR), 
	enquiryPurpose VARCHAR2(255 CHAR), 
	enquiryAmount VARCHAR2(255 CHAR), 
	CIBIL_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (enquiryId)
  );
  commit;

 CREATE TABLE QCIB_CIBILEnquirySummary 
   (	summaryId NUMBER(19,0) NOT NULL ENABLE, 
	enquiryPurpose VARCHAR2(255 CHAR), 
	total VARCHAR2(255 CHAR), 
	past30Days VARCHAR2(255 CHAR), 
	past12Months VARCHAR2(255 CHAR), 
	past24Months VARCHAR2(255 CHAR), 
	recent VARCHAR2(255 CHAR),
	CIBIL_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (summaryId)
  );
  commit;

  CREATE TABLE QCIB_CIBILIDDetails
   (	identityId NUMBER(19,0) NOT NULL ENABLE, 
	idType VARCHAR2(255 CHAR), 
	idNumber VARCHAR2(255 CHAR), 
	issueDate VARCHAR2(255 CHAR), 
	expirationDate VARCHAR2(255 CHAR), 
	CIBIL_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (identityId)
  );
  commit;

  CREATE TABLE QCIB_CIBILEmailContact
   (	uniqueId NUMBER(19,0) NOT NULL ENABLE, 
	email VARCHAR2(255 CHAR),
	CIBIL_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (uniqueId)
  );
  commit;


  CREATE TABLE QCIB_CIBIL_ACCOUNT_M_SUM 
   (	SUMMARYID NUMBER(19,0) NOT NULL ENABLE, 
	accountTotal VARCHAR2(255 CHAR), 
	accountOverdue VARCHAR2(255 CHAR), 
	accountZeroBalance VARCHAR2(255 CHAR), 
	advanceHighCRSancAmt VARCHAR2(255 CHAR), 
	currentBalance VARCHAR2(255 CHAR), 
  overdueBalance VARCHAR2(255 CHAR), 
  recentDate VARCHAR2(255 CHAR), 
  oldestDate VARCHAR2(255 CHAR), 
	CIBIL_UNIQUE_ID NUMBER(19,0), 
	 PRIMARY KEY (SUMMARYID)
  );
 
commit;


CREATE SEQUENCE QCIB_CIBIL_ACCOUNT_M_SUM_SQC  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE ;
 commit;


 CREATE SEQUENCE  QCIB_CIBIL_RESP_SQC  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE ;
commit;
 CREATE SEQUENCE QCIB_CIBILIDDetails_SQC  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE ;
 commit;
 CREATE SEQUENCE  QCIB_CIBILEmailContact_SQC  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE ;
 commit;
 CREATE SEQUENCE QCIB_CIBILEnquiryDetails_SQC  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE ;
 commit;
 CREATE SEQUENCE  QCIB_CIBILEnquirySummary_SQC  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE ;
 commit;
 CREATE SEQUENCE QCIB_CIBILACCOUNTSUMMARY_SQC  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE ;
 commit;
 CREATE SEQUENCE  QCIB_CIBILDISPUTEDETAILS_SQC  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE ;
 commit;
 CREATE SEQUENCE  QCIB_CIBILPAYMENTHISTORY_SQC  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE ;
 commit;
 CREATE SEQUENCE  QCIB_CIBIL_TELP_SQC  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE ;
 commit;
 CREATE SEQUENCE  QCIB_CIBIL_ADDR_SQC  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE ;
 commit;
 CREATE SEQUENCE  QCIB_CIBIL_ACC_SQC  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE ;
 commit;
 CREATE SEQUENCE  QCIB_CIBIL_EMP_SQC  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 CACHE 20 NOORDER  NOCYCLE ;
 commit;
  

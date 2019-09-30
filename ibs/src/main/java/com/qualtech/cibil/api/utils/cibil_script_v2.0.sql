ALTER TABLE QCIB_CIBILRESPONSEENTITY ADD (PRINTABLEREPORT1 CLOB);
ALTER TABLE QCIB_CIBILRESPONSEENTITY ADD (PDFBYTEARRAY1 CLOB);
UPDATE QCIB_CIBILRESPONSEENTITY SET PRINTABLEREPORT1 = PRINTABLEREPORT;
UPDATE QCIB_CIBILRESPONSEENTITY SET PDFBYTEARRAY1 = PDFBYTEARRAY;
ALTER TABLE QCIB_CIBILRESPONSEENTITY DROP COLUMN PRINTABLEREPORT;
ALTER TABLE QCIB_CIBILRESPONSEENTITY DROP COLUMN PDFBYTEARRAY;
ALTER TABLE QCIB_CIBILRESPONSEENTITY RENAME COLUMN PRINTABLEREPORT1 TO PRINTABLEREPORT;
ALTER TABLE QCIB_CIBILRESPONSEENTITY RENAME COLUMN PDFBYTEARRAY1 TO PDFBYTEARRAY;
alter table QCIB_CIBILRESPONSEENTITY add(CIBIL_RESPONSE CLOB,CIBIL_REQUEST CLOB);
ALTER TABLE QCIB_CIBILRESPONSEENTITY DROP COLUMN SCORE;
ALTER TABLE QCIB_CIBILRESPONSEENTITY DROP COLUMN SCORECARDNAME;
ALTER TABLE QCIB_CIBILRESPONSEENTITY DROP COLUMN SCOREDATE;
commit;

CREATE TABLE QCIB_CIBIL_SCORE(
SCORE_ID NUMBER(16,0) NOT NULL,
CIBIL_UNIQUE_ID NUMBER(16,0),
SCORE VARCHAR2(20),
SCORE_NAME VARCHAR2(50),
SCORE_DATE VARCHAR2(20),
CONSTRAINT QCIB_CIBIL_SCORE_PK PRIMARY KEY (SCORE_ID)
);
commit;

CREATE SEQUENCE  QCIB_CIBIL_SCORE_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 100 NOCACHE  NOORDER  NOCYCLE ;
commit;
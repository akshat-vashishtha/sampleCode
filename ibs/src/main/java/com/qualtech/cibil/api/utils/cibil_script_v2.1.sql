ALTER TABLE QCIB_CIBILENQUIRYSUMMARY ADD (PAST90DAYS1 VARCHAR2(20 BYTE));
commit;
ALTER TABLE QCIB_CIBIL_ACCOUNT_M_SUM ADD (suitfiled_wilfulCount VARCHAR2(20 BYTE));
commit;
ALTER TABLE QCIB_CIBIL_ACCOUNT_M_SUM ADD (writtenOffAndSettleCount VARCHAR2(20 BYTE));
commit;
ALTER TABLE QCIB_CIBIL_SCORE ADD (SCORE_FACTORS CLOB);
commit;
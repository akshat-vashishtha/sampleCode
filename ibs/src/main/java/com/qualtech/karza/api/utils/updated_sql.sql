alter table QCIB_K_DL_KYC_COV_DTL add ISSUE_DATE varchar2(250);
COMMIT;
alter table QCIB_K_DL_KYC_RES add IMG CLOB;
COMMIT;
alter table QCIB_K_REQ_RES add INTERNAL_REQ CLOB;
COMMIT;
alter table QCIB_KARZA_SUB add INTERNAL_REQ CLOB;
COMMIT;
alter table QCIB_K_REQ_RES add INTERNAL_RES CLOB;
COMMIT;
alter table QCIB_KARZA_SUB add INTERNAL_RES CLOB;
COMMIT;
update QM_SYS_CONFIGURATION set paramvalue='https://testapi.karza.in/v2/dl' where paramname='com.karza.dl.url';
commit;
update QM_SYS_CONFIGURATION set paramvalue='https://testapi.karza.in/v2/tele' where paramname='com.karza.tele.url';
commit;
update QM_SYS_CONFIGURATION set paramvalue='https://testapi.karza.in/v2/lpg' where paramname='com.karza.lpg.url';
commit;
update QM_SYS_CONFIGURATION set paramvalue='https://testapi.karza.in/v2/elec' where paramname='com.karza.elec.url';
commit;
alter table QCIB_CREDIT_REQ_RES add INTERNAL_REQ CLOB;
COMMIT;
alter table QCIB_CREDIT_SUB add INTERNAL_REQ CLOB;
COMMIT;
alter table QCIB_CREDIT_REQ_RES add INTERNAL_RES CLOB;
COMMIT;
alter table QCIB_CREDIT_SUB add INTERNAL_RES CLOB;
COMMIT;







package com.qualtech.crif.api.common.dto;

import java.util.List;

public class ApiResponseDto {
private String tokenFlag;
private String tokenValue;
private String validationFlag;
private String validationMsg;
private String caseId;
private List<ApiUrlDto> apiUrlDto;
private CflReqDetailsDto cflReqDetailsDto;
private CflResponseDto cflResponseDto;
private IciciReqDto iciciReqDto;
private IciciResponseDto iciciResponseDto;
private String bankId;
private String productId;
private HdfcRequestDto hdfcRequestDto;
private PNBrequestDto pnBrequestDto;
private int userId;
public PNBrequestDto getPnBrequestDto() {
	return pnBrequestDto;
}
public void setPnBrequestDto(PNBrequestDto pnBrequestDto) {
	this.pnBrequestDto = pnBrequestDto;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public HdfcRequestDto getHdfcRequestDto() {
	return hdfcRequestDto;
}
public void setHdfcRequestDto(HdfcRequestDto hdfcRequestDto) {
	this.hdfcRequestDto = hdfcRequestDto;
}
public String getProductId() {
	return productId;
}
public void setProductId(String productId) {
	this.productId = productId;
}
public String getBankId() {
	return bankId;
}
public void setBankId(String bankId) {
	this.bankId = bankId;
}
public String getCaseId() {
	return caseId;
}
public void setCaseId(String caseId) {
	this.caseId = caseId;
}
public CflResponseDto getCflResponseDto() {
	return cflResponseDto;
}
public void setCflResponseDto(CflResponseDto cflResponseDto) {
	this.cflResponseDto = cflResponseDto;
}
public IciciResponseDto getIciciResponseDto() {
	return iciciResponseDto;
}
public void setIciciResponseDto(IciciResponseDto iciciResponseDto) {
	this.iciciResponseDto = iciciResponseDto;
}
public IciciReqDto getIciciReqDto() {
	return iciciReqDto;
}
public void setIciciReqDto(IciciReqDto iciciReqDto) {
	this.iciciReqDto = iciciReqDto;
}
public CflReqDetailsDto getCflReqDetailsDto() {
	return cflReqDetailsDto;
}
public void setCflReqDetailsDto(CflReqDetailsDto cflReqDetailsDto) {
	this.cflReqDetailsDto = cflReqDetailsDto;
}
public String getTokenFlag() {
	return tokenFlag;
}
public void setTokenFlag(String tokenFlag) {
	this.tokenFlag = tokenFlag;
}
public String getTokenValue() {
	return tokenValue;
}
public void setTokenValue(String tokenValue) {
	this.tokenValue = tokenValue;
}
public String getValidationFlag() {
	return validationFlag;
}
public void setValidationFlag(String validationFlag) {
	this.validationFlag = validationFlag;
}
public String getValidationMsg() {
	return validationMsg;
}
public void setValidationMsg(String validationMsg) {
	this.validationMsg = validationMsg;
}
public List<ApiUrlDto> getApiUrlDto() {
	return apiUrlDto;
}
public void setApiUrlDto(List<ApiUrlDto> apiUrlDto) {
	this.apiUrlDto = apiUrlDto;
}


}
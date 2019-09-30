package com.qualtech.cibil.api.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.cibil.api.common.dto.Address;
import com.qualtech.cibil.api.common.dto.TelePhone;
import com.qualtech.cibil.api.common.dto.UniqueId;
import com.qualtech.cibil.api.interfaces.CibilConvertRequestToTUEFInt;
import com.qualtech.cibil.api.request.CibilApiRequest;
@Service
public class CibilConvertRequestToTUEF  implements CibilConvertRequestToTUEFInt
{
	
	
	@Autowired PropertyFile env;
	public String convertRequestToTUEF(CibilApiRequest apiRequest)
	{
		StringBuilder tuefString=new StringBuilder("TUEF12");
		String memberreferencenumber=env.getString("cibil.userID.referenceNumber");
		tuefString.append(memberreferencenumber);
		/**********************for  reference number should 27******************************/
		for(int i=0;i<(27-memberreferencenumber.length());i++)
		{
			tuefString.append(" ");

		}
		/**********************for  reference number should 27******************************/
		String userID=env.getString("cibil.userID");
		/**********************for  UserID should 30******************************/
		tuefString.append(userID);
		for(int i=0;i<(30-userID.length());i++)
		{
			tuefString.append(" ");

		}
		/**********************for  UserID should 30******************************/

		/**********************for  Password should 30******************************/
		String password=env.getString("cibil.Password");
		tuefString.append(password);
		for(int i=0;i<(30-password.length());i++)
		{
			tuefString.append(" ");

		}

		/**********************for  Password should 30******************************/
		tuefString.append(env.getString("cibil.enquiryPurpose"));
		/**********************for  Enquiry Amount******************************/
		String enquiryAmount=env.getString("cibil.enquiryAmount");
		for(int i=0;i<(9-enquiryAmount.length());i++)
		{
			tuefString.append("0");

		}
		tuefString.append(enquiryAmount);
		/**********************for  Enquiry Amount******************************/

		/***********************fill with blanks *******************************/
		tuefString.append("   ");
		/***********************fill with blanks *******************************/


		/********************for score type************************************/
		
		String scoreType = apiRequest.getPayload().getTransaction().get(0).getScoreType();
		if(scoreType!=null 
				&& !scoreType.equalsIgnoreCase("") 
				&& (scoreType.equalsIgnoreCase("00") 
						|| scoreType.equalsIgnoreCase("01") 
						|| scoreType.equalsIgnoreCase("02") 
						|| scoreType.equalsIgnoreCase("03") 
						|| scoreType.equalsIgnoreCase("04")
						|| scoreType.equalsIgnoreCase("06")
					)
			)
		{
			tuefString.append(scoreType);
		}
		else
		{
			tuefString.append("00");//Default No Score
		}
		/********************for score type************************************/


		/********************for OutPutFormat************************************/
		tuefString.append(env.getString("cibil.OutPutFormat")+"1");
		/********************for OutPutFormat************************************/

		/*************************for Input Out Put Media***********************/
		tuefString.append(env.getString("cibil.InputOutPutMedia"));
		/*************************for Input Out Put Media***********************/


		tuefString.append(env.getString("cibil.authenticationMethod"));// for Authentication Method

		///////////////////////////////////////////////PN Segment Starts///////////////////////////////////////////////////
		tuefString.append("PN03N01");
		///////////////////////////////////////////////PN Segment Ends///////////////////////////////////////////////////


		///////////////////////////////////////////for Names////////////////////////////////////////////////////////////
		tuefString.append("01");
		String firstName=apiRequest.getPayload().getTransaction().get(0).getFirstName();
		String fNameLength=Commons.getLengthforCibil(firstName);
		tuefString.append(fNameLength);
		tuefString.append(firstName);
		tuefString.append("0200");
		String middleName=apiRequest.getPayload().getTransaction().get(0).getMiddleName();
		tuefString.append("03");
		String mNameLength=Commons.getLengthforCibil(middleName);
		tuefString.append(mNameLength);
		tuefString.append(middleName);
		tuefString.append("0400");
		String lastName=apiRequest.getPayload().getTransaction().get(0).getLastName();
		tuefString.append("05");
		String lNameLength=Commons.getLengthforCibil(lastName);
		tuefString.append(lNameLength);
		tuefString.append(lastName);

		///////////////////////////////////////////for  Names////////////////////////////////////////////////////////////


		///////////////////////////////////////////for date of birth/////////////////////////////////////////////////////

		tuefString.append("0708");
		String dateOfBirth=apiRequest.getPayload().getTransaction().get(0).getDob();
		tuefString.append(dateOfBirth);

		///////////////////////////////////////////for date of birth/////////////////////////////////////////////////////


		///////////////////////////for gender////////////////////////////////////
		tuefString.append("0801");
		String gender=apiRequest.getPayload().getTransaction().get(0).getGender();
		tuefString.append(gender);
		//////////////////////////for gender///////////////////////////////////




		///////////////////////////////////////////for Identification/////////////////////////////////////////////////////
		List<UniqueId> uniqueids=apiRequest.getPayload().getTransaction().get(0).getUniqueids();
		for(int i=0;i<uniqueids.size();i++)
		{
			String IdType=uniqueids.get(i).getIdType();
			String IdNumber=uniqueids.get(i).getIdNo();
			String idNumberlength=Commons.getLengthforCibil(IdNumber);
			tuefString.append("ID03I0"+(i+1)+"0102"+IdType);
			tuefString.append("02"+idNumberlength+IdNumber);

		}
		///////////////////////////////////////////for Identification/////////////////////////////////////////////////////

		///////////////////////////////////////////for Telephone//////////////////////////////////////////////////////////
		List<TelePhone> telephones=apiRequest.getPayload().getTransaction().get(0).getTelephones();
		for(int i=0;i<telephones.size();i++)
		{

			String telephoneNumber=telephones.get(i).getTelephoneNumber();
			String tNlength=Commons.getLengthforCibil(telephoneNumber);
			String telephoneExtn=telephones.get(i).getTelephoneExtn();
			String telephoneExtnLength=Commons.getLengthforCibil(telephoneExtn);
			String telephoneType=telephones.get(i).getTelephoneType();
			tuefString.append("PT03T0"+(i+1)+"01");
			tuefString.append(tNlength+telephoneNumber);
			tuefString.append("02"+telephoneExtnLength+telephoneExtn);
			tuefString.append("0302"+telephoneType);
		}
		///////////////////////////////////////////for Telephone//////////////////////////////////////////////////////////


		///////////////////////////////////////////for Address/////////////////////////////////////////////////////////////
		List<Address>  addresses= apiRequest.getPayload().getTransaction().get(0).getAddresses();
		for(int i=0;i<addresses.size();i++)
		{
			Address address=addresses.get(i);
			String addressline1=address.getAddressline1();
			String addressline2=address.getAddressline2();
			String addressline3=address.getAddressline3();
			String addressline4=address.getAddressline4();
			String addressline5=address.getAddressline5();
			String addresscategory=address.getAddresscategory();
			String pincode=address.getPincode();
			String PinCodeLength=Commons.getLengthforCibil(pincode);
			String statecode=address.getStatecode();
			String residenceCode=address.getResidencecode();
			tuefString.append("PA03A0"+(i+1));
			String addressleine1length=Commons.getLengthforCibil(addressline1);
			String addressleine2length=Commons.getLengthforCibil(addressline2);
			String addressleine3length=Commons.getLengthforCibil(addressline3);
			String addressleine4length=Commons.getLengthforCibil(addressline4);
			String addressleine5length=Commons.getLengthforCibil(addressline5);


			tuefString.append("01");
			tuefString.append(addressleine1length);
			tuefString.append(addressline1);

			tuefString.append("02");
			tuefString.append(addressleine2length);
			tuefString.append(addressline2);

			tuefString.append("03");
			tuefString.append(addressleine3length);
			tuefString.append(addressline3);

			tuefString.append("04");
			tuefString.append(addressleine4length);
			tuefString.append(addressline4);

			tuefString.append("05");
			tuefString.append(addressleine5length);
			tuefString.append(addressline5);




			tuefString.append("0602");
			tuefString.append(statecode);
			tuefString.append("07");
			tuefString.append(PinCodeLength);
			tuefString.append(pincode);
			tuefString.append("0802");
			tuefString.append(addresscategory);
			tuefString.append("0902");
			tuefString.append(residenceCode);

		}


		/////////////////////////////////////////for Address//////////////////////////////////////////////////////////////

		//////////////////////////////////////////////End Tag/////////////////////////////////////////////////////////////
		int length=tuefString.length()+15;
		tuefString.append("ES05");
		StringBuilder lengthtuef=new StringBuilder("00000"+length);
		String lastsize=lengthtuef.substring(lengthtuef.length()-5,lengthtuef.length());
		tuefString.append(lastsize);
		tuefString.append("0102**");
		//////////////////////////////////////////////End Tag/////////////////////////////////////////////////////////////

		//civilRequest	//TUEF121234343                    HF63891001_UATC2C             cOyx@c5bv                     00000010000   01011CCLPN03N010111VISWANATHAN02000310RAMAMOORHY0400050007080511199408012ID03I0101020102101234567890ID03I02010202021112345678901ID03I03010203021112345678901ID03I0401020502041212ID03I050102060203123PT03T010110994019919702101234567890030200PA03A010140C-7/  B  WING 4TH FLOOR, ROOM NO 79,CHAR0235KOP GURUKRIPA, KANDIVLI (W), MUMBAI0306MUMBAI040005000602270706400067080202090202PA03A02010312302000311NAVI MUMBAI040005000602270706400708080201090201ES05005650102**
		//civilResponse //{"TUEF":"TUEF121234343                    0000HF63891001_UATC2C             100100033535803042017193352PN03N010111VISWANATHAN0310RAMAMOORHY07080511199408012PT03T01011099401991970208123213130302019001YPT03T020108254599810302009001YPT03T03011097640099340302009001YPT03T040110994019919702101234567890030200SC10CIBILTUSCR0102010202100308030420170405000-1PA03A010140C-7/  B  WING 4TH FLOOR, ROOM NO 79,CHAR0235KOP GURUKRIPA, KANDIVLI (W), MUMBAI0306MUMBAI06022707064000670802030902021008210520139001YPA03A0201031230311NAVI MUMBAI06022707064007080802010902011008201020119001YPA03A030140C-7/  B  WING 4TH FLOOR, ROOM NO 79,CHAR0235KOP GURUKRIPA, KANDIVLI (W), MUMBAI0506MUMBAI0602270706400067080202090202100820102011IQ04I0010108310320170413NOT DISCLOSED050200060510000IQ04I0020108310320170413NOT DISCLOSED050200060510000IQ04I0030108291120160413NOT DISCLOSED050200060510000IQ04I0040108281120160413NOT DISCLOSED050200060510000IQ04I0050108261120160413NOT DISCLOSED050200060510000IQ04I0060108261120160413NOT DISCLOSED050200060510000IQ04I0070108251120160413NOT DISCLOSED050200060510000IQ04I0080108241120160413NOT DISCLOSED050200060510000IQ04I0090108231120160413NOT DISCLOSED050200060510000IQ04I0100108221120160413NOT DISCLOSED050200060510000IQ04I0110108091120160413NOT DISCLOSED050200060510000IQ04I0120108020820160413NOT DISCLOSED050200060599999IQ04I0130108020820160413NOT DISCLOSED050200060599999IQ04I0140108020820160413NOT DISCLOSED050200060599999IQ04I0150108010820160413NOT DISCLOSED050200060510000IQ04I0160108270720160413NOT DISCLOSED050200060510000IQ04I0170108250720160413NOT DISCLOSED050200060510000IQ04I0180108050720160413NOT DISCLOSED050200060510000IQ04I0190108050720160413NOT DISCLOSED050200060510000IQ04I0200108020720160413NOT DISCLOSED050200060510000IQ04I0210108190620160413NOT DISCLOSED050200060510000IQ04I0220108190120160413NOT DISCLOSED050200060510000IQ04I0230108180120160413NOT DISCLOSED050200060510000IQ04I0240108181220150413NOT DISCLOSED050200060510000IQ04I0250108181220150413NOT DISCLOSED050200060510000IQ04I0260108181220150413NOT DISCLOSED050200060510000IQ04I0270108181220150413NOT DISCLOSED050200060510000IQ04I0280108181220150413NOT DISCLOSED050200060510000IQ04I0290108171220150413NOT DISCLOSED050200060510000IQ04I0300108111220150413NOT DISCLOSED050200060510000IQ04I0310108091220150413NOT DISCLOSED050200060510000IQ04I0320108031220150413NOT DISCLOSED050200060510000IQ04I0330108281020150413NOT DISCLOSED050200060510000IQ04I0340108191020140413NOT DISCLOSED050200060510000IQ04I0350108101020140413NOT DISCLOSED050200060510000IQ04I0360108061020140413NOT DISCLOSED050200060510000IQ04I0370108160720140413NOT DISCLOSED050200060510000IQ04I0380108100720140413NOT DISCLOSED050200060510000IQ04I0390108070720140413NOT DISCLOSED050200060510000IQ04I0400108030720140413NOT DISCLOSED050200060510000IQ04I0410108020720140413NOT DISCLOSED050200060510000IQ04I0420108010720140413NOT DISCLOSED050200060510000IQ04I0430108010720140413NOT DISCLOSED050200060510000IQ04I0440108240620140413NOT DISCLOSED050200060510000IQ04I0450108240620140413NOT DISCLOSED050200060510000IQ04I0460108230620140413NOT DISCLOSED050200060510000IQ04I0470108200620140413NOT DISCLOSED050200060510000IQ04I0480108190620140413NOT DISCLOSED050200060510000IQ04I0490108170620140413NOT DISCLOSED050200060510000IQ04I0500108230520140413NOT DISCLOSED050200060510000IQ04I0510108210520130413NOT DISCLOSED050251060510000IQ04I0520108210520130413NOT DISCLOSED050251060510000IQ04I0530108210520130413NOT DISCLOSED050251060510000IQ04I0540108210520130413NOT DISCLOSED050200060510000IQ04I0550108010520130413NOT DISCLOSED050206060550000IQ04I0560108250420130413NOT DISCLOSED050200060510000IQ04I0570108240420130413NOT DISCLOSED050200060510000IQ04I0580108230420130413NOT DISCLOSED050200060510000IQ04I0590108030420130413NOT DISCLOSED050200060510000IQ04I0600108120420120413NOT DISCLOSED050200060510000IQ04I0610108110420120413NOT DISCLOSED050200060510000IQ04I0620108300120120413NOT DISCLOSED05020306071000000IQ04I0630108211220110413NOT DISCLOSED050200060510000IQ04I0640108201220110413NOT DISCLOSED050200060510000IQ04I0650108191220110413NOT DISCLOSED050200060510000IQ04I0660108231120110413NOT DISCLOSED05021006041000IQ04I0670108181120110413NOT DISCLOSED05021006041000IQ04I0680108091120110413NOT DISCLOSED05021006041000IQ04I0690108071120110413NOT DISCLOSED05021006041000IQ04I0700108071120110413NOT DISCLOSED05021006041000IQ04I0710108041120110413NOT DISCLOSED05021006041000IQ04I0720108041120110413NOT DISCLOSED05021006041000IQ04I0730108011120110413NOT DISCLOSED05021006041000IQ04I0740108011120110413NOT DISCLOSED05021006041000IQ04I0750108211020110413NOT DISCLOSED05021006041000IQ04I0760108201020110413NOT DISCLOSED05021006041000ES0700046600102**"}


		

		return tuefString.toString();
	}

}

package kh.edu.cstad.payment_khqr.feature.khqr;

import kh.gov.nbc.bakong_khqr.BakongKHQR;
import kh.gov.nbc.bakong_khqr.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndividualInfoServiceImpl implements IndividualInfoService {

    @Override
    public KHQRResponse<KHQRData> processIndividualInfo(IndividualInfo individualInfo) {

        individualInfo.setBakongAccountId(individualInfo.getBakongAccountId());
        individualInfo.setAccountInformation(individualInfo.getAccountInformation());
        individualInfo.setAcquiringBank(individualInfo.getAcquiringBank());
        individualInfo.setCurrency(KHQRCurrency.USD);
        individualInfo.setAmount(individualInfo.getAmount());
        individualInfo.setMerchantName(individualInfo.getMerchantName());
        individualInfo.setMerchantCity(individualInfo.getMerchantCity());
        individualInfo.setBillNumber(individualInfo.getBillNumber());
        individualInfo.setMobileNumber(individualInfo.getMobileNumber());
        individualInfo.setStoreLabel(individualInfo.getStoreLabel());
        individualInfo.setTerminalLabel(individualInfo.getTerminalLabel());
        individualInfo.setUpiAccountInformation(individualInfo.getUpiAccountInformation());
        individualInfo.setPurposeOfTransaction(individualInfo.getPurposeOfTransaction());
        individualInfo.setMerchantAlternateLanguagePreference(individualInfo.getMerchantAlternateLanguagePreference());
        individualInfo.setMerchantCityAlternateLanguage(individualInfo.getMerchantCityAlternateLanguage());
        individualInfo.setMerchantNameAlternateLanguage(individualInfo.getMerchantNameAlternateLanguage());

        KHQRResponse<KHQRData> response = BakongKHQR.generateIndividual(individualInfo);

        if(response.getKHQRStatus().getCode() == 0){
            System.out.println("QR Data: " + response.getData().getQr());
            System.out.println("MD5: " + response.getData().getMd5());
        }else{
            System.out.println("Error: " + response.getKHQRStatus().getMessage());
        }

        return response;
    }

    @Override
    public String verification(String qrData) {

        KHQRResponse<CRCValidation> response = BakongKHQR.verify(qrData);

        boolean result = response.getData().isValid();

        return "Verification Result: " + result;
    }

    @Override
    public String decode(String qrData) {

        KHQRResponse<KHQRDecodeData> response = BakongKHQR.decode(qrData);

        return "Decoded Data: " + response.getData().toString();
    }

    @Override
    public String generateDeepLink(String url, String qrData, SourceInfo sourceInfo) {

        SourceInfo source = new SourceInfo();
        source.setAppName(sourceInfo.getAppName());
        source.setAppIconUrl(sourceInfo.getAppIconUrl());
        source.setAppDeepLinkCallback(sourceInfo.getAppDeepLinkCallback());

        KHQRResponse<KHQRDeepLinkData> response = BakongKHQR.generateDeepLink(url, qrData, source);

        if(response.getKHQRStatus().getCode() == 0){
            return response.getData().getShortLink();
        }else{
            return response.getKHQRStatus().getMessage();
        }
    }
}

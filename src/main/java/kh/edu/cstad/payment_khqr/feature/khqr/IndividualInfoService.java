package kh.edu.cstad.payment_khqr.feature.khqr;

import kh.gov.nbc.bakong_khqr.model.*;

public interface IndividualInfoService {

    KHQRResponse<KHQRData> processIndividualInfo(IndividualInfo individualInfo);
    String verification(String qrData);
    String decode(String qrData);
    String generateDeepLink(String url, String qrData, SourceInfo sourceInfo);
}

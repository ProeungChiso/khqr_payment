package kh.edu.cstad.payment_khqr.feature.khqr;

import kh.gov.nbc.bakong_khqr.model.IndividualInfo;
import kh.gov.nbc.bakong_khqr.model.KHQRData;
import kh.gov.nbc.bakong_khqr.model.KHQRResponse;

public interface IndividualInfoService {

    KHQRResponse<KHQRData> processIndividualInfo(IndividualInfo individualInfo);
    String verification(String qrData);

}

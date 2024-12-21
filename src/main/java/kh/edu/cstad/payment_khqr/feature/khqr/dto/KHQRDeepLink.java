package kh.edu.cstad.payment_khqr.feature.khqr.dto;

import kh.gov.nbc.bakong_khqr.model.SourceInfo;

public record KHQRDeepLink(
        String url,
        String qrData,
        SourceInfo sourceInfo
) {
}

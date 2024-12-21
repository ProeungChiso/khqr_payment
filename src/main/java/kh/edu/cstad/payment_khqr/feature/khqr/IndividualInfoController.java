package kh.edu.cstad.payment_khqr.feature.khqr;

import kh.edu.cstad.payment_khqr.feature.khqr.dto.KHQRDeepLink;
import kh.gov.nbc.bakong_khqr.model.IndividualInfo;
import kh.gov.nbc.bakong_khqr.model.KHQRData;
import kh.gov.nbc.bakong_khqr.model.KHQRResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/khqr")
@RequiredArgsConstructor
public class IndividualInfoController {

    private final IndividualInfoService individualInfoService;

    @PostMapping
    public ResponseEntity<KHQRResponse<KHQRData>> createKHQRData(
            @RequestBody IndividualInfo individualInfo
    ) {
        KHQRResponse<KHQRData> response = individualInfoService.processIndividualInfo(individualInfo);

        if(response.getKHQRStatus().getCode() == 0){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyKHQRData(
            @RequestBody String qrData
    ) {
        String response = individualInfoService.verification(qrData);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/decode")
    public ResponseEntity<String> decodeKHQRData(
            @RequestBody String qrData
    ) {
        String response = individualInfoService.decode(qrData);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/deeplink")
    public ResponseEntity<String> generateDeepLink(
            @RequestBody KHQRDeepLink request
            ) {
        String response = individualInfoService.generateDeepLink(request.url(), request.qrData(), request.sourceInfo());
        return ResponseEntity.ok(response);
    }
}

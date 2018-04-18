package eu.chargetime.ocpp.test.core.json

import eu.chargetime.ocpp.model.core.ResetType
import spock.util.concurrent.PollingConditions

class JSONResetSpec extends JSONBaseSpec {

    def "Central System sends a Reset request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        when:
        centralSystem.sendResetRequest(ResetType.Hard)

        then:
        conditions.eventually {
            assert chargePoint.hasHandledResetRequest()
            assert centralSystem.hasReceivedResetConfirmation("Accepted")
        }
    }
}

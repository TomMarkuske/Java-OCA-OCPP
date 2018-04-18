package eu.chargetime.ocpp.test.core.json

import spock.util.concurrent.PollingConditions

class JSONChangeConfigurationSpec extends JSONBaseSpec
{
    def "Central System sends a ChangeConfiguration request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)

        given:
        conditions.eventually {
            assert !centralSystem.isClosed()
        }
        when:
        centralSystem.sendChangeConfigurationRequest("key", "value")

        then:
        conditions.eventually {
            assert chargePoint.hasHandledChangeConfigurationRequest()
            assert centralSystem.hasReceivedChangeConfigurationConfirmation()
        }
    }
}

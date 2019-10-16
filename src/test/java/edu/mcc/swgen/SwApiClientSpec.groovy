package edu.mcc.swgen

import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Unroll

class SwApiClientSpec extends Specification {
    def restTemplate = Mock(RestTemplate)
    def swApiService = new SwApiClient(restTemplate)
    def userAgent = 'java/' + System.getProperty('java.version')

    def 'sets user-agent header'() {
        when:
        def result = swApiService.retrieveCharacters(null)
        then:
        result
        1 * restTemplate.exchange('/people/?page={page}', HttpMethod.GET, _, SwApiCharacterList, _) >>  {  args ->
            def entity = args[2] as HttpEntity
            assert entity.headers.getFirst('User-Agent') == userAgent
            return new ResponseEntity<>(new SwApiCharacterList(), HttpStatus.OK)
        }
        0 * _
    }

    @Unroll
    def 'retrieve character list where page = #page'() {
        1 * restTemplate.exchange('/people/?page={page}', HttpMethod.GET, _, SwApiCharacterList, _) >> { args ->
            def params = args[4]
            assert params['page'] == result
            return new ResponseEntity<>(new SwApiCharacterList(), HttpStatus.OK)
        }
        0 * _

        expect:
        swApiService.retrieveCharacters(page)

        where:
        page || result
        null || 1
        3    || 3
    }
}

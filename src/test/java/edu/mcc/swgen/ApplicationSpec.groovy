package edu.mcc.swgen

import org.springframework.boot.web.client.RestTemplateBuilder
import spock.lang.Specification

class ApplicationSpec extends Specification {
    def application = new Application()

    def 'generates a rest-template with base uri'() {
        when:
        def restTemplate = application.restTemplate(new RestTemplateBuilder())

        then:
        restTemplate
        restTemplate.uriTemplateHandler['rootUri'] == 'https://swapi.co/api'
    }

}

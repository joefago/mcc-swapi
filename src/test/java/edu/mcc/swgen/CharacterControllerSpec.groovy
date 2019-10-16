package edu.mcc.swgen

import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.view.InternalResourceViewResolver
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class CharacterControllerSpec extends Specification {

    def swApiService = Mock(SwApiClient)
    def characterController = new CharacterController(swApiService)
    def mvc = MockMvcBuilders.standaloneSetup(characterController)
            .setViewResolvers(new InternalResourceViewResolver())
            .build()

    def characterList = new SwApiCharacterList(count: 2, results: [
            new SwApiCharacter(name: 'Luke Skywalker', eyeColor: 'blue'),
            new SwApiCharacter(name: 'R2-D2', eyeColor: 'red')
    ])

    def 'gets a list of characters'() {
        when:
        def model = mvc.perform(get('/character-list'))
                .andReturn().getModelAndView().getModel()
        then:
        model
        model['characters']['count'] == 2
        model['characters']['results'][0].name == 'Luke Skywalker'
        model['characters']['results'][0].eyeColor == 'blue'
        1 * swApiService.retrieveCharacters(null) >> characterList
        0 * _
    }

    def 'gets a list of characters by page'() {
        when:
        mvc.perform(get('/character-list?page=5'))
        then:
        1 * swApiService.retrieveCharacters(5)
        0 * _
    }
}

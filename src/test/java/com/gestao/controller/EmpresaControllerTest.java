package com.gestao.controller;



import com.gestao.domain.empresa.Empresa;
import com.gestao.domain.empresa.EmpresaRepository;
import com.gestao.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;


import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;





import com.gestao.domain.vagas.dto.CreateVagaDTO;

import static junit.runner.Version.id;
import static org.junit.Assert.assertTrue;

//rodar o teste
@RunWith(SpringRunner.class)
//criar porta para rodar a aplicação
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EmpresaControllerTest {

    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private EmpresaRepository empresaRepository;

    //para rodar os testes
    @Before
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())

                .build();
    }


//@Test
//public void criar_novo_emprego() throws Exception{
//    var empresa = Empresa.builder()
//               .description("EMPRESA_DESCRIPTION")
//            .email("email@empresa.com.br")
//            .password("1234")
//            .name("Testes")
//            .webSite("https://www.globo.com/")
//
//            .build();
//
//empresa=empresaRepository.saveAndFlush(empresa);
//
//var createVagaDTO= CreateVagaDTO.builder()
//        .nivelDaVaga("NIVELDAVAGA_TEST")
//        .beneficio("BENEFICIO_TEST")
//        .description("DESCRIPTION_TEST")
//        .modalidadeVaga("MODALIDADEVAGA_TEST")
//        .requisitos("REQUISITOS_TEST")
//        .build();
//
//var result=mvc.perform(MockMvcRequestBuilders.post("/empresa/vagas")
//       .contentType(MediaType.APPLICATION_JSON)
//                .content(TestUtils.objectToJson(createVagaDTO))
//          .header("Authorization", TestUtils.generateToken(empresa.getId(), "casalitech"))
//        )
//     .andExpect(MockMvcResultMatchers.status().isOk());
//   System.out.println(result);
//}


    @Test
    public void should_not_be_able_to_create_a_new_job_if_company_not_found() throws Exception{
        var createVagaDTO= CreateVagaDTO.builder()
                .nivelDaVaga("NIVELDAVAGA_TEST")
                .beneficio("BENEFICIO_TEST")
                .description("DESCRIPTION_TEST")
                .modalidadeVaga("MODALIDADEVAGA_TEST")
                .requisitos("REQUISITOS_TEST")
                .build();

     try {


        mvc.perform(MockMvcRequestBuilders.post("/empresa/vagas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJson(createVagaDTO))

                        .header("Authorization", TestUtils.generateToken(Long.parseLong(id()), "casalitech")));

     }catch (Exception e ){
         System.out.println(e);
     }
              //  .andExpect(result ->assertTrue(result.getResolvedException() instanceof EmpresaNotFoundException) );


    }
}







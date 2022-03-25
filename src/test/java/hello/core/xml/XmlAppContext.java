package hello.core.xml;


import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlAppContext {
    
    @Test
    public void xmlAppContext() throws Exception{
        //given
        GenericXmlApplicationContext gc = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = gc.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
        //when
        
        //then
    }
        
}

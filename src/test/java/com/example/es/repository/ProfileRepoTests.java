package com.example.es.repository;


import com.example.es.domain.Member;
import com.example.es.domain.Profile;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit // 테스트 결과commit
public class ProfileRepoTests {

    @Autowired
    MemberRepository memberRepo;


    @Autowired
    ProfileRepository profileRepo;


    @Test
    public void testInsertMembers(){
        IntStream.range(1,101).forEach(i ->{
            Member member = new Member();

            member.setUid("user"+i);
            member.setName("사용자"+i);
            member.setPassword("pw"+i);

            memberRepo.save(member);
        });
    }
    @Test
    public void testInsertProfiles(){


        Member member = new Member();
        member.setUid("user1");

        for( int i =1 ; i < 5 ;i++){

            Profile profile = new Profile();
            profile.setFname("face"+ i + ".jpg");
            if( i ==1 ){
                profile.setCurrent(true);
            }

            profile.setMember(member);


            profileRepo.save(profile);
        }


    }
}

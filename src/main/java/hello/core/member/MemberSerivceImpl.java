package hello.core.member;

public class MemberSerivceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberSerivceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }// 생성자를 통해서 memberRepository 주입 : 생성자 주입

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

package m320.projekt.lib.interfaces;

public interface AuthenticationService<SU_REQ, SU_RES, SI_REQ, SI_RES> {
    SU_RES signUp(SU_REQ dto);
    SI_RES signIn(SI_REQ dto);
}

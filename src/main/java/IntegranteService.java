import java.util.Collection;

public interface IntegranteService {

    public void addIntegrante(Integrante integrante);
    public Collection<Integrante> getIntegrantes();
    public Integrante getIntegrante(String id);
    public Integrante editIntegrante (Integrante integrante) throws IntegranteException;
    public void deleteIntegrante(String id);
    public boolean integranteExists(String id);
}

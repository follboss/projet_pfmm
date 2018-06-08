package observer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Observable {
	
    private List<Observateur> m_observateurs;
	
	public Observable() 

    {

        m_observateurs = new LinkedList<Observateur>();

    }

    

    public void notifierObservateurs()

    {

       Iterator<Observateur> it = m_observateurs.iterator();

        // Notifier tous les observers

       while(it.hasNext()){

           Observateur obs = it.next();

           obs.update();

       }

    }

    

    public void ajouterObservateur(Observateur observateur)

    {

        // On ajoute un abonn� � la liste en le plaçant en premier (impl�ment� en pull).

            // On pourrait placer cet observateur en dernier (impl�ment� en push, plus commun).

        m_observateurs.add(observateur);

    }

    

    public void supprimerObservateur(Observateur observateur)

    {

        // Enlever un abonn� a la liste

        m_observateurs.remove(observateur);

    }

    

}

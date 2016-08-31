package fr.pizzeria.ihm;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import fr.pizzeria.model.AbstractPersonne;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.CompteStat;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;

public class AfficherCompteStatAction extends Action {

	public AfficherCompteStatAction(IhmHelper helper) {
		super("Afficher les statistiques", helper);
	}

	public void execute() {
		int nbComptes = 0;
		double totalSolde = 0;
		double moySolde;
		double minSolde = 0;
		double maxSolde = 0;
		
		boolean isFirstOf = true; // est le premier de la liste
		
		System.out.println("**** Statistiques ****");
		
		// on récupère les listes de clients et de livreurs pour les statistiques
		Collection<Client> clients = this.helper.getStockageClient().findAll();
		Collection<Livreur> livreurs = this.helper.getStockageLivreur().findAll();
		
		Collection<CompteStat> personne = new ArrayList<CompteStat>();	// création de la liste de CompteStat
		personne.addAll(clients);	// on ajoute tous les clients à la liste personne
		personne.addAll(livreurs);	// on ajoute tous les livreurs à la liste personne
		
		////////////////////////////////////////
		// utilisation des expressions lambda //
		////////////////////////////////////////
		nbComptes = (int) personne.stream().count();
		totalSolde = personne.stream().map(t -> t.getSolde()).reduce((t1, t2) -> t1+t2).get().doubleValue();
		moySolde = personne.stream().collect(Collectors.averagingDouble(CompteStat::getSolde));
		minSolde = personne.stream().min((c1, c2) -> Double.compare(c1.getSolde(),c2.getSolde())).get().getSolde();
		maxSolde = personne.stream().max((c1, c2) -> Double.compare(c1.getSolde(),c2.getSolde())).get().getSolde();
		
		/* Remplacé par les expressions lambda
		// on parcourt la liste de personnes pour calculer toutes les statistiques
		for (CompteStat personneEnCours : personne ) {
			totalSolde +=  personneEnCours.getSolde(); // on ajoute le solde du personneEnCours au total
			
			// on différencie les traitements entre le premier de la liste et le reste
			// le solde du premier initialise le min et max
			// pour le reste, on compare le solde en cours avec les min et max
			if (isFirstOf){
				minSolde = personneEnCours.getSolde();
				maxSolde = personneEnCours.getSolde();
				isFirstOf = false;
			}
			else { 
				if(compareTo(personneEnCours, maxSolde) == 1){
					maxSolde = personneEnCours.getSolde();
				}
				if(compareTo(personneEnCours, minSolde) == -1){
					minSolde = personneEnCours.getSolde();
				}
			}
		}
		// à la fin du parcourt, on  récupère la taille de la liste et on calcule la moyenne
		nbComptes = personne.size();
		moySolde = totalSolde / nbComptes;
		*/
		
		// on affiche les statistiques !!!
		System.out.println("Nombre de comptes = " + nbComptes);
		System.out.println("Total solde = " + totalSolde + " €");
		System.out.println("Moyenne solde = " + moySolde + " €");
		System.out.println("Solde le plus faible = " + minSolde + " €");
		System.out.println("Solde le plus élevé = " + maxSolde + " €");
	}

	public int compareTo(CompteStat p1, double solde){
		if(p1.getSolde() > solde){
			return 1;
		}
		else if(p1.getSolde() < solde){
			return -1;
		}
		else {
			return 0;
		}
	}
}

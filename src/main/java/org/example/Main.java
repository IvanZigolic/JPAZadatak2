package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.model.Ingridient;
import org.example.model.Meal;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("kitchen");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Ingridient ing1 = new Ingridient();
        ing1.setName("Pasata");
        em.persist(ing1);

        Ingridient ing2 = new Ingridient();
        ing2.setName("Mljeveno meso");
        em.persist(ing2);

        Ingridient ing3 = new Ingridient();
        ing3.setName("Luk");
        em.persist(ing3);

        Ingridient ing4 = new Ingridient();
        ing4.setName("Voda");
        em.persist(ing4);

        Ingridient ing5 = new Ingridient();
        ing5.setName("Meso");
        em.persist(ing5);

        Ingridient ing6 = new Ingridient();
        ing6.setName("Krumpir");
        em.persist(ing6);

        Meal meal1 = new Meal();
        meal1.setName("Spagete bolonjez");
        meal1.getIngridients().add(ing1);
        meal1.getIngridients().add(ing2);
        meal1.getIngridients().add(ing3);
        em.persist(meal1);

        Meal meal2 = new Meal();
        meal2.setName("Gulas");
        meal2.getIngridients().add(ing1);
        meal2.getIngridients().add(ing3);
        meal2.getIngridients().add(ing4);
        meal1.getIngridients().add(ing5);
        em.persist(meal2);

        Meal meal3 = new Meal();
        meal3.setName("Musaka");
        meal3.getIngridients().add(ing5);
        meal3.getIngridients().add(ing6);
        meal3.getIngridients().add(ing3);
        em.persist(meal3);

        //Ispis jela i sastojaka
        List<Meal> meals = em.createQuery("select m from Meal m", Meal.class).getResultList();
        for (Meal m : meals) {
            System.out.println("Naziv jela: " + m.getName());
            for (Ingridient i : m.getIngridients()) {
                System.out.println("Sastojci: " + i.getName());
            }
        }
//        //Brisanje
//        Meal meal = em.find(Meal.class, 1L);
//        if (meal != null) {
//            em.remove(meal);
//        }
        tx.commit();
        em.close();
        emf.close();
    }
}

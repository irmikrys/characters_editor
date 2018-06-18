package cdi;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.Optional;

@Reductional
@Interceptor
public class ReductionInterceptor {

    @PersistenceContext(name = "com.irmikrys.soa")
    private EntityManager entityManager;

    @AroundInvoke
    public Object manageCreation(InvocationContext invocationContext) throws Exception {

        TypedQuery<Integer> query = entityManager.createQuery(
          "SELECT max(e.fortune) FROM Element e ", Integer.class
        );

        //category, name, propAmount, propType, power
        Object[] contextParams = invocationContext.getParameters();
        System.out.println(Arrays.asList(contextParams));

        Integer amountToCheck = Integer.valueOf(contextParams[2].toString());
        Optional<Integer> maxAmount = Optional.ofNullable(query.getSingleResult());

        maxAmount.ifPresent(max -> {
            if(amountToCheck > max) contextParams[2] = max;
        });
        System.out.println(Arrays.asList(contextParams));

        return invocationContext.proceed();
    }
}

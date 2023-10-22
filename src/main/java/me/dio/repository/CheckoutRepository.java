package me.dio.repository;

import me.dio.model.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * O CheckoutRepository é uma interface que atua como um repositório de dados para a entidade Checkout no sistema de atendimento ao mercado.
 * Essa interface estende a interface JpaRepository, fornecida pelo Spring Data JPA, para aproveitar os métodos padrão de acesso a dados (CRUD) e outros recursos relacionados à persistência.
 */
@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
}

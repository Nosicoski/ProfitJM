package com.example.Profit.Repository;

import com.example.Profit.Model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetallePedidoRepository extends JpaRepository<DetallePedido,Long> {
}

/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.example.repository.jpa.repository;

import io.shardingsphere.example.repository.api.entity.OrderEncrypt;
import io.shardingsphere.example.repository.api.repository.OrderEncryptRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class JPAOrderEncryptRepositoryImpl implements OrderEncryptRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void createTableIfNotExists() {
        throw new UnsupportedOperationException("createTableIfNotExists for JPA");
    }
    
    @Override
    public void truncateTable() {
        throw new UnsupportedOperationException("truncateTable for JPA");
    }
    
    @Override
    public void dropTable() {
        throw new UnsupportedOperationException("dropTable for JPA");
    }
    
    @Override
    public Long insert(final OrderEncrypt orderEncrypt) {
        entityManager.persist(orderEncrypt);
        return orderEncrypt.getOrderId();
    }
    
    @Override
    public void delete(final Long aesId) {
        Query query = entityManager.createQuery("DELETE FROM OrderEncryptEntity e WHERE e.aesId = ?1");
        query.setParameter(1, aesId);
        query.executeUpdate();
    }
    
    @Override
    public void update(final String aesId) {
        Query query = entityManager.createQuery("UPDATE OrderEncryptEntity SET e.aesId = 11 WHERE e.aesId = ?1");
        query.setParameter(1, aesId);
        query.executeUpdate();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<OrderEncrypt> selectAll() {
        return (List<OrderEncrypt>) entityManager.createQuery("SELECT e FROM OrderEntity o, OrderEncryptEntity e WHERE o.orderId = e.orderId").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<OrderEncrypt> selectRange() {
        return (List<OrderEncrypt>) entityManager.createQuery("SELECT e FROM OrderEntity o, OrderEncryptEntity e WHERE o.orderId = e.orderId AND o.userId BETWEEN 1 AND 5").getResultList();
    }
}

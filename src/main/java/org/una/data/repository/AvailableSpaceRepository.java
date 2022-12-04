/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.data.entities.AvailableSpace;

@Repository
public interface AvailableSpaceRepository extends JpaRepository<AvailableSpace, Long> {

}

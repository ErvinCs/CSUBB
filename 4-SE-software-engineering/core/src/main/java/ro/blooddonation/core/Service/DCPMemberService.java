package ro.blooddonation.core.Service;

import ro.blooddonation.core.Domain.DCPMember;

import java.util.List;
import java.util.Optional;

public interface DCPMemberService
{
    /**
     * @param item
     */
    DCPMember add(DCPMember item);

    /**
     * @param id
     */
    void remove(Long id);

    /**
     * @param id
     */
    Optional<DCPMember> update(Long id, DCPMember newItem);

    /**
     * @return
     */
    List<DCPMember> findAll();

    /**
     * @param id
     * @return
     */
    Optional<DCPMember> findOne(Long id);
}

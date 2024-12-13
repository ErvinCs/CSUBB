package ro.blooddonation.core.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.blooddonation.core.Domain.DCPMember;
import ro.blooddonation.core.Repo.DCPMemberRepo;

import java.util.List;
import java.util.Optional;

@Service
public class DCPMemberServiceImpl implements DCPMemberService
{
    private static final Logger log = LoggerFactory.getLogger(DCPMemberService.class);

    @Autowired
    private DCPMemberRepo dcpMemberRepo;

    /**
     *
     * @param item
     * @return
     */
    @Override
    @Transactional
    public DCPMember add(DCPMember item)
    {
        log.trace("addDCPMember: DCPMember={}", item);

        dcpMemberRepo.save(item);

        log.trace("addDCPMember --- method finished");

        return item;
    }

    /**
     * @param id
     */
    @Override
    @Transactional
    public void remove(Long id)
    {
        log.trace("removeDCPMember: id={}", id);

        dcpMemberRepo.deleteById(id);

        log.trace("removeDCPMember --- method finished");
    }

    /**
     * @param id
     */
    @Override
    @Transactional
    public Optional<DCPMember> update(Long id, DCPMember newItem)
    {
        log.trace("updateDCPMember: DCPMember={}", newItem);

        Optional<DCPMember> dcpMemberOptional = dcpMemberRepo.findById(id);

        dcpMemberOptional.ifPresent(r ->
                {
                }
        );

        log.trace("updateDCPMember: DCPMemberOptional={}", dcpMemberOptional);

        return dcpMemberOptional;
    }

    /**
     * @return
     */
    @Override
    public List<DCPMember> findAll()
    {
        log.trace("findAllDCPMembers");

        List<DCPMember> dcpMembers = dcpMemberRepo.findAll();

        log.trace("findAll: DCPMembers={}", dcpMembers);

        return dcpMembers;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<DCPMember> findOne(Long id)
    {
        log.trace("findDCPMember: DCPMemberId={}", id);

        Optional<DCPMember> dcpMember = dcpMemberRepo.findById(id);

        log.trace("findDCPMember: DCPMember={}", dcpMember);

        return dcpMember;
    }
}

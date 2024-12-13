package ro.blooddonation.core.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.blooddonation.core.Domain.Request;
import ro.blooddonation.core.Repo.RequestRepo;

import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService
{
    private static final Logger log = LoggerFactory.getLogger(RequestServiceImpl.class);

    @Autowired
    private RequestRepo requestRepo;

    /**
     *
     * @param item
     * @return
     */
    @Override
    @Transactional
    public Request add(Request item)
    {
        log.trace("addRequest: request={}", item);

        requestRepo.save(item);

        log.trace("addRequest --- method finished");

        return item;
    }

    /**
     * @param id
     */
    @Override
    @Transactional
    public void remove(Long id)
    {
        log.trace("removeRequest: id={}", id);

        requestRepo.deleteById(id);

        log.trace("removeRequest --- method finished");
    }

    /**
     * @param id
     */
    @Override
    @Transactional
    public Optional<Request> update(Long id, Request newItem)
    {
        log.trace("updateRequest: request={}", newItem);

        Optional<Request> requestOptional = requestRepo.findById(id);

        requestOptional.ifPresent(r ->
                {
                    r.setUrgency(newItem.getUrgency());
                    r.setStatus(newItem.getStatus());
                }
        );

        log.trace("updateRequest: requestOptional={}", requestOptional);

        return requestOptional;
    }

    /**
     * @return
     */
    @Override
    public List<Request> findAll()
    {
        log.trace("findAllRequests");

        List<Request> requests = requestRepo.findAll();

        log.trace("findAll: Requests={}", requests);

        return requests;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Request> findOne(Long id)
    {
        log.trace("findRequest: RequestId={}", id);

        Optional<Request> request = requestRepo.findById(id);

        log.trace("findRequest: Request={}", request);

        return request;
    }
}

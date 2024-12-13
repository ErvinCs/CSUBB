package web.Controller;

import core.Domain.Client;
import core.Service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.Converter.ClientConverter;
import web.dto.ClientDto;
import web.dto.ClientsDto;
import web.dto.EmptyJsonResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ClientController {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;


    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ClientsDto getClients() {
        log.trace("getClients");

        List<Client> clients = clientService.findAll();

        log.trace("getClients: clients={}", clients);

        return new ClientsDto(clientConverter.convertModelsToDtos(clients));
    }


    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.PUT)
    public ClientDto updateBook(@PathVariable final Long clientId,
                                @RequestBody final ClientDto clientDto) {
        log.trace("updateClient: clientId={}, clientDtoMap={}", clientId, clientDto);

        Client client = new Client(clientDto.getName(), clientDto.getCountry());
        client.setID(clientId);

        Optional<Client> clientOptional = clientService.updateClient(clientId, client);

        Map<String, ClientDto> result = new HashMap<>();
        if (clientOptional.isPresent())
            result.put("client", clientConverter.convertModelToDto(clientOptional.get()));
        else
            result.put("client", clientConverter.convertModelToDto(new Client()));

        log.trace("updateClient: result={}", result);

        return result.get("client");
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public ClientDto createClient(@RequestBody final ClientDto clientDto) {
        log.trace("createClient: clientDtoMap={}", clientDto);

        Client c = new Client(clientDto.getName(), clientDto.getCountry());
        c.setID(clientDto.getId());
        Client client = clientService.addClient(c);

        ClientDto result = clientConverter.convertModelToDto(client);

        log.trace("createClient: result={}", result);

        return result;
    }


    @RequestMapping(value = "clients/{clientId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteClient(@PathVariable final Long clientId) {
        log.trace("deleteClient: clientId={}", clientId);

        clientService.deleteClient(clientId);

        log.trace("deleteClient - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
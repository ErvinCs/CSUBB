package web.Converter;
import core.Domain.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import web.dto.ClientDto;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {

    private static final Logger log = LoggerFactory.getLogger(ClientConverter.class);

    @Override
    public Client convertDtoToModel(ClientDto dto) {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto clientDto = new ClientDto(client.getName(), client.getCountry());
        clientDto.setId(client.getID());
        //in ClientDto - @Builder
        return clientDto;
    }
}

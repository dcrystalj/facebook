package si.fb.posiljanjeobvestilstoritev;

import javax.ejb.EJB;

import si.fb.posiljanjeobvestilstoritevsporocila.ObjectFactory;
import si.fb.posiljanjeobvestilstoritevsporocila.SporociloTip;
import si.fb.posiljanjeobvestilstoritevsporocila.TipSporocilaResponseEnum;
import code.beanEJB.*;

@javax.jws.WebService (endpointInterface="si.fb.posiljanjeobvestilstoritev.PosiljanjeObvestilStoritev", targetNamespace="http://www.fb.si/PosiljanjeObvestilStoritev", serviceName="PosiljanjeObvestilStoritev", portName="PosiljanjeObvestilStoritevSOAP")
public class PosiljanjeObvestilStoritevSOAPImpl{
	@EJB
	PosiljanjeObvestilBean pob;
	
	public TipSporocilaResponseEnum posljiSporocilo(SporociloTip posljiSporociloRequest) {
		try {
			//Posljemo obvestilo, vsebino najdemo v vsebini zahtevka SOAP
			pob.posljiObvestilo(posljiSporociloRequest.getEmail(), posljiSporociloRequest.getNaslov(), posljiSporociloRequest.getVsebina());
			return TipSporocilaResponseEnum.OK;
		} catch(Exception e) {
			//Èe gre narobe zahtevku vrnemo napako
			return TipSporocilaResponseEnum.ERROR;
		}
	}

}
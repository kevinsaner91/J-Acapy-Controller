
package simplewebapp.beans;

import java.util.List;

import com.owlike.genson.annotation.JsonProperty;

public class Credentials {

	private List<CredentialResult> results = null;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public Credentials() {
	}

	/**
	 *
	 * @param results
	 */
	public Credentials(@JsonProperty("results") List<CredentialResult> results) {

		this.results = results;
	}

	public List<CredentialResult> getResults() {
		return results;
	}

	public void setResults(List<CredentialResult> results) {
		this.results = results;
	}

}
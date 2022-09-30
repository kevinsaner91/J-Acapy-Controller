package simplewebapp.beans;

import com.owlike.genson.annotation.JsonProperty;

public class CredentialResult {

	private String referent;

	private Attrs attrs;

	private String schemaId;

	private String credDefId;

	private Object revRegId;

	private Object credRevId;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public CredentialResult() {
	}

	/**
	 *
	 * @param referent
	 * @param schemaId
	 * @param credDefId
	 * @param revRegId
	 * @param credRevId
	 * @param attrs
	 */
	public CredentialResult(@JsonProperty("referent") String referent, @JsonProperty("attrs") Attrs attrs,
			@JsonProperty("schemaId") String schemaId, @JsonProperty("credDefId") String credDefId,
			@JsonProperty("revRegId") Object revRegId, @JsonProperty("credRevId") Object credRevId) {
		this.referent = referent;
		this.attrs = attrs;
		this.schemaId = schemaId;
		this.credDefId = credDefId;
		this.revRegId = revRegId;
		this.credRevId = credRevId;
	}

	public String getReferent() {
		return referent;
	}

	public void setReferent(String referent) {
		this.referent = referent;
	}

	public Attrs getAttrs() {
		return attrs;
	}

	public void setAttrs(Attrs attrs) {
		this.attrs = attrs;
	}

	public String getSchemaId() {
		return schemaId;
	}

	public void setSchemaId(String schemaId) {
		this.schemaId = schemaId;
	}

	public String getCredDefId() {
		return credDefId;
	}

	public void setCredDefId(String credDefId) {
		this.credDefId = credDefId;
	}

	public Object getRevRegId() {
		return revRegId;
	}

	public void setRevRegId(Object revRegId) {
		this.revRegId = revRegId;
	}

	public Object getCredRevId() {
		return credRevId;
	}

	public void setCredRevId(Object credRevId) {
		this.credRevId = credRevId;
	}

}
package contacts;

class Organization extends Contact {
    private String organizationName;
    private String address;


    Organization() {
    }


    public String getOrganizationName() {
        return organizationName;
    }

    public String getAddress() {
        return address;
    }


    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String getShortContactInfo() {
        return getOrganizationName();
    }

    @Override
    String getFullContactInfo() {
        return getShortContactInfo() + " " + getAddress() + " " + getNumber();
    }

    @Override
    public String getFields() {
        return "name, address, number";
    }

    @Override
    public void setField(String field, String value) {
        switch (field) {
            case "name":
                setOrganizationName(value);
                break;
            case "address":
                setAddress(value);
                break;
            case "number":
                setNumber(value);
                break;
        }
    }

    @Override
    void getDetails() {
        System.out.println("Organization name: " + this.getOrganizationName());
        System.out.println("Address: " + this.getAddress());
    }
}

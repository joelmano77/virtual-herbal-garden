import axios from 'axios';

const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080';

const api = axios.create({
  baseURL: API_BASE_URL,
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request interceptor for adding auth headers if needed
api.interceptors.request.use(
  (config) => {
    // Add any auth headers here if needed
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Response interceptor for error handling
api.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    console.error('API Error:', error);
    if (error.response) {
      // Server responded with error status
      console.error('Response error:', error.response.data);
    } else if (error.request) {
      // Request was made but no response received
      console.error('Request error:', error.request);
    } else {
      // Something else happened
      console.error('Error:', error.message);
    }
    return Promise.reject(error);
  }
);

export const plantService = {
  // Get all plants with optional filters
  getAllPlants: (params = {}) => {
    return api.get('/plants', { params })
      .then(response => response.data)
      .catch(error => {
        console.error('Error fetching plants:', error);
        throw error;
      });
  },

  // Get plant by ID
  getPlantById: (id) => {
    return api.get(`/plants/${id}`)
      .then(response => response.data)
      .catch(error => {
        console.error(`Error fetching plant ${id}:`, error);
        throw error;
      });
  },

  // Add new plant
  addPlant: (plant) => {
    return api.post('/plants', plant)
      .then(response => response.data)
      .catch(error => {
        console.error('Error adding plant:', error);
        throw error;
      });
  },

  // Update plant
  updatePlant: (id, plant) => {
    return api.put(`/plants/${id}`, plant)
      .then(response => response.data)
      .catch(error => {
        console.error(`Error updating plant ${id}:`, error);
        throw error;
      });
  },

  // Delete plant
  deletePlant: (id) => {
    return api.delete(`/plants/${id}`)
      .then(response => response.data)
      .catch(error => {
        console.error(`Error deleting plant ${id}:`, error);
        throw error;
      });
  },
};

export default api;
